package com.example.forumBackEnd.service;

import com.example.forumBackEnd.mapper.SearchMapper;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Name;
import javax.swing.text.html.StyleSheet;
import java.util.List;

@Service
public class SearchService {
    @Resource
    private UserService userService;

    @Resource
    private SearchMapper searchMapper;

    public List<User> searchByName(String userName){
        List<User> nameList = searchMapper.searchByName(userName);
        return nameList;
    }

    public List<Post> serchByTag(String Tag){
        List<Post> postList = searchMapper.searchByTag(Tag);
        return postList;
    }

    public List<Post> searchByTitle(String Title){
        List<Post> postList = searchMapper.searchByTitle(Title);
        return postList;
    }
}
