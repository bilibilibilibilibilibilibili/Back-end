package com.example.forumBackEnd.service;


import com.example.forumBackEnd.mapper.PostMapper;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.PostDao;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @jakarta.annotation.Resource
    private TokenUtil tokenUtil;
    @jakarta.annotation.Resource
    private PostMapper postMapper;


    public int addPost(Post post){
        post.setMediaResources(new ArrayList<>());
        int affectRows =  postMapper.addPost(post);
        System.out.println(affectRows);
        System.out.println(post.getId());
        if(affectRows <= 0){
            return 0;
        }
        return post.getId();


    }

    public List<Post> selectPostByLastComment(int offset)  {
        return postMapper.selectPostByLastComment(offset);
    }

    public List<Post> selectPostByTime(int offset) {
        return postMapper.selectPostByTime(offset);
    }

}
