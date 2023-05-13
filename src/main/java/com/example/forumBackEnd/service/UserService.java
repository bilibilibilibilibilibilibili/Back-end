package com.example.forumBackEnd.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.forumBackEnd.mapper.UserMapper;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.enumClass.Identity;
import com.example.forumBackEnd.pojo.enumClass.SexEnum;
import com.example.forumBackEnd.pojo.enumClass.UserStatus;
import com.example.forumBackEnd.util.TokenUtil;
import com.example.forumBackEnd.util.UsernameUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MailService mailService;
    /**
     * 注册账号
     * @param user
     * @return 0=成功 1=失败 2=已被占用
     */
    @Transactional
    public int createAccount(User user){   //因为返回的是json所以直接用map收集对象
        User dbUser = userMapper.selectUserByEmail(user.getEmail());
//        System.out.println(dbUser);
        if (null == dbUser){
            //雪花算法生成确认码
            String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();
            //盐(加密用）
            String salt = RandomUtil.randomString(6);
            //加密密码：原始密码+盐
            String md5Pwd = SecureUtil.md5(user.getPassword() + salt);
            //激活失效时间：24小时
            LocalDateTime ldt = LocalDateTime.now().plusDays(1);
            //初始化账号信息
            user.setSalt(salt);
            user.setPassword(md5Pwd);
            user.setConfirmCode(confirmCode);
            user.setActivateTime(ldt);
            user.setUserName(UsernameUtil.getRandomGuestName());
            user.setStatus(UserStatus.GUEST);
            user.setIdentity(Identity.STUDENT);
            user.setSex(SexEnum.UNKNOWN);
            user.setBanTag(new ArrayList<>());
            user.setFans(new ArrayList<>());
            user.setFollow(new ArrayList<>());
            user.setPost(new ArrayList<>());
            //新增账号
            int result = userMapper.insertUser(user);
//            int result = 1;
            if (result > 0){
                //发送邮件
//            String activationUrl = "http://localhost:8080/user/activation?confirmCode=" + confirmCode;
//            mailService.sendMailForActivationAccount(activationUrl,user.getEmail());
                return 0;
            }else{
                return 1;
            }
        }
        return 2;
    }

    /**
     * 根据邮箱登录账号
     * @param user
     * @return 0=找不到用户 1=密码错误 2=登录成功
     */
    public int loginAccount(User user){
        //根据邮箱查询用户
        User dbUser = userMapper.selectUserByEmail(user.getEmail());
        //查询不到结果，返回：该用户不存在或未激活
        if(dbUser == null){
            return 0;
        }
        //查询到一个用户，进行密码比对
        boolean verify = verifyUser(user, dbUser);
        //密码不一致，返回：用户名或密码错误
        if(!verify){
            return 1;
        }
        return 2;
    }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
    public Map<String, Object> activationAccount(String confirmCode) {
        Map<String, Object> resultMap = new HashMap<>();
        //根据确认码查询用户
        User user = userMapper.selectUserByConfirmCode(confirmCode);
        //判断激活时间是否超时
        boolean after = LocalDateTime.now().isAfter(user.getActivateTime());
        if (after) {
            resultMap.put("code", 400);
            resultMap.put("message", "链接已失效，请重新注册");
            return resultMap;
        }
        //根据确认码查询用户并修改状态值为1（可用）
        int result = userMapper.updateUserByConfirmCode(confirmCode);
        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "激活成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "激活失败");
        }
        return resultMap;
    }
    public String selectPasswordById(int id) {
        return userMapper.selectPasswordById(id);
    }

    private boolean verifyUser(User user, User dbUser) {
        return Objects.equals(dbUser.getPassword(), SecureUtil.md5(user.getPassword() + dbUser.getSalt()));
    }
}
