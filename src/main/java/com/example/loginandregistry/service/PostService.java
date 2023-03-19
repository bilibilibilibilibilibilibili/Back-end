package com.example.loginandregistry.service;


import com.example.loginandregistry.mapper.PostMapper;
import com.example.loginandregistry.pojo.Post;
import com.example.loginandregistry.pojo.response.BasicResponse;
import com.example.loginandregistry.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Service
public class PostService {
    @jakarta.annotation.Resource
    private TokenUtil tokenUtil;
    @jakarta.annotation.Resource
    private PostMapper postMapper;


    public BasicResponse addPost(Post post){
        BasicResponse basicResponse = new BasicResponse();
        return basicResponse;
    }

    public List<Post> selectPostByLastComment(int offset)  {
        return postMapper.selectPostByLastComment(offset);
    }

    public List<Post> selectPostByTime(int offset) {
        return postMapper.selectPostByTime(offset);
    }

}
