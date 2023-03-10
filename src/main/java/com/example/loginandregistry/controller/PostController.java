package com.example.loginandregistry.controller;

import com.example.loginandregistry.pojo.response.BasicResponse;
import com.example.loginandregistry.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="post",produces = "application/json; charset=UTF-8")
public class PostController {
    @Resource
    private PostService postService;

    @PostMapping("test")
    public BasicResponse httpTest(String json){
        System.out.println(json);
        return new BasicResponse();
    };

    @PostMapping("add")
    public BasicResponse addPost(){
        return new BasicResponse();
    };


}
