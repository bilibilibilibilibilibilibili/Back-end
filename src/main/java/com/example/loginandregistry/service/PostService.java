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
    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private PostMapper postMapper;

    @RequestMapping("addPost")
    public BasicResponse addPost(Map<String,Object> map){
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
