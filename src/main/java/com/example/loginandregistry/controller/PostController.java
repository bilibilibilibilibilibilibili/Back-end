package com.example.loginandregistry.controller;


import com.example.loginandregistry.pojo.Post;
import com.example.loginandregistry.pojo.PostTest;
import com.example.loginandregistry.pojo.Tag;
import com.example.loginandregistry.pojo.response.BasicResponse;
import com.example.loginandregistry.pojo.request.PostGetRequest;
import com.example.loginandregistry.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.loginandregistry.pojo.MediaResource;

import java.util.List;

@RestController
@RequestMapping(path="post",produces = "application/json; charset=UTF-8")
public class PostController {
    @jakarta.annotation.Resource
    private PostService postService;

    @PostMapping("test")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse httpTest(@RequestBody MediaResource mediaResource){
        System.out.println(mediaResource.getName());
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setMessage("发送成功");

        basicResponse.setData(mediaResource);

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

    @PostMapping("by-last-comment")
    @JsonIgnoreProperties(ignoreUnknown = true)    // 仅供测试
    public BasicResponse selectPostByLastComment(@RequestBody PostGetRequest request) {
        int offset = request.getOffset();
        List<PostTest> postList =  postService.selectPostByLastComment(offset);
        return BasicResponse.getSuccessResponse("按最近评论，第"+offset+"页",
                postList);
    }

//    @PostMapping("by-time")
//    public List<Post> selectPostByTime(int offset) {
//        return postService.selectPostByTime(offset);
//    }
}
