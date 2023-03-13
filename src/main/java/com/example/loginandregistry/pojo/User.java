package com.example.loginandregistry.pojo;
import com.example.loginandregistry.pojo.enumClass.Identity;
import com.example.loginandregistry.pojo.enumClass.SexEnum;
import com.example.loginandregistry.pojo.enumClass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;   //自动构造setter，getter，builder，@RequiredArgsConstructor，@ToString，@EqualsAndHashCode
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 创建实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    /**
     * 屏蔽Tag
     */
    private Tag[] banTag;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 粉丝
     */
    private String[] fans;
    /**
     * 关注的人
     */
    private String[] follow;
    /**
     * 年级
     */
    private String grade;
    /**
     * 用户uid
     */
    private String id;
    /**
     * 账号身份与权限
     * ADMIN -> 管理员
     * ORGANIZATION -> 组织/团体/部门
     * PRIOR_ADMIN -> 最高权限
     * STUDENT -> 学生
     * SUPER_ADMIN -> 超管
     * TEACHER -> 教师
     */
    private Identity identity;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 密码（已加密）
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 发布过的文章id
     */
    private String[] post;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 盐加密公钥
     */
    private String salt;
    /**
     * 性别（生理）
     * male -> 男
     * female -> 女
     * unknown -> 未知
     */
    private SexEnum sex;
    /**
     * 账号状态
     * ban -> 封禁中
     * cancel -> 已注销
     * guest -> 临时账号
     * normal -> 正常
     */
    private UserStatus status;
    /**
     * 学号
     */
    private String studentNumber;
    /**
     * 用户名
     */
    private String userName;
    private String confirmCode;
    private LocalDateTime activationTime;
}