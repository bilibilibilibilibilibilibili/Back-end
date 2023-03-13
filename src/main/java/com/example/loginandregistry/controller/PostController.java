package com.example.loginandregistry.controller;


import com.example.loginandregistry.pojo.Post;
import com.example.loginandregistry.pojo.Tag;
import com.example.loginandregistry.pojo.response.BasicResponse;
import com.example.loginandregistry.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.loginandregistry.pojo.Resource;

import java.util.List;

@RestController
@RequestMapping(path="post",produces = "application/json; charset=UTF-8")
public class PostController {
    @jakarta.annotation.Resource
    private PostService postService;

    @PostMapping("test")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse httpTest(@RequestBody Resource resource){
        System.out.println(resource.getName());
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setMessage("发送成功");

        basicResponse.setData(resource);

        return basicResponse;
    };
    @PostMapping("test2")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse httpTest2(@RequestBody List<Tag> tagList) {
        System.out.println(tagList);
        System.out.println(tagList.get(0) != null);
        for(Tag tag:tagList){
            System.out.println(tag.getName());
        }
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setMessage("发送成功");

        basicResponse.setData(tagList);

        return basicResponse;
    };

    @PostMapping("add")
    public BasicResponse addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PostMapping("ByComment")
    public List<Post> selectPostByLastComment(int offset) {
        return postService.selectPostByLastComment(offset);
    }

    @PostMapping("ByTime")
    public List<Post> selectPostByTime(int offset) {
        return postService.selectPostByTime(offset);
    }
}
