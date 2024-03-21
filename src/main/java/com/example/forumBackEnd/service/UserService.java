package com.example.forumBackEnd.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.forumBackEnd.mapper.UserMapper;
import com.example.forumBackEnd.pojo.Collection;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.enumClass.Identity;
import com.example.forumBackEnd.pojo.enumClass.SexEnum;
import com.example.forumBackEnd.pojo.enumClass.UserStatus;
import com.example.forumBackEnd.util.MailUtil;
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

import static jakarta.servlet.http.HttpServletResponse.*;

@Service
public class UserService {

    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MailUtil mailUtil;
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
            user.setFans(0);
            user.setFollow(0);
            //新增账号
            int result = userMapper.insertUser(user);
//            int result = 1;
            if (result > 0){
                //发送邮件
                System.out.println("database added, sending email");
                String activationUrl = "http://localhost:8080/user/activation?userId="+user.getId()+"&confirmCode=" + confirmCode;
                mailUtil.sendMailForActivationAccount(activationUrl, user.getEmail());
                return 0;  // 成功
            }else{
                return 1;  // 因为mybatis内部错误失败
            }
        }
        return 2;  // 邮箱已被注册
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
            return -1;
        }
        return dbUser.getId();
    }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
    public int activationAccount(String userId, String confirmCode) {
        //根据确认码查询用户
        User user = userMapper.selectUserByConfirmCode(confirmCode);
        if(null == user){ return SC_BAD_REQUEST; }
        //判断激活时间是否超时
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(user.getActivateTime())) { return SC_BAD_REQUEST; }
        //根据id修改状态值为NORMAL（可用）
        int result = userMapper.activateUserById(userId);
        if (result > 0) {
            return SC_OK;
        } else {
            return SC_INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 根据用户uid获得信息
     * @param userId
     * @return
     */
    public User getUserById(int userId){
        User user = userMapper.selectUserById(userId);
        if (user!=null){
            // 信息脱敏
            user.setPassword("");
            user.setConfirmCode("");
            user.setSalt("");
            user.setIdNumber("");
            return user;
        } else {
            return null;
        }
    }

    private boolean verifyUser(User user, User dbUser) {
        return Objects.equals(dbUser.getPassword(), SecureUtil.md5(user.getPassword() + dbUser.getSalt()));
    }

    public int addCollection(Collection collection){
        int collection1  = userMapper.addCollection(collection);
        if(collection1 <= 0){
            return 0;
        }
        return collection.getUserId();
    }

    public int deleteCollection(Collection collection){
        int collection2 = userMapper.deleteCollection(collection);
        if (collection2 <= 0){
            return 0;
        }
        return 1;
    }
}
