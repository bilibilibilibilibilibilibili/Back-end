package com.example.demo1.backend_info.service;

import com.example.demo1.backend_info.mapper.UserMapper;
import com.example.demo1.backend_info.entity.UserBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    //将dao层属性注入service层
    @Resource
    private UserMapper userMapper;

    public UserBean LoginIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }

    public void Insert(String name,String password){
        userMapper.saveInfo(name, password);
    }
}