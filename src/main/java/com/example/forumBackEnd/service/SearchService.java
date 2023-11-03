package com.example.forumBackEnd.service;

import com.example.forumBackEnd.mapper.SearchMapper;
import com.example.forumBackEnd.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
public class SearchService {
    @Resource
    private UserService userService;

    @Resource
    private SearchMapper searchMapper;

    public List<User> searchByName(String Name){
        List<User> nameList = searchMapper.searchByName(Name);
        return nameList;
    }
}
