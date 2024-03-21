package com.example.forumBackEnd.controller;


import com.example.forumBackEnd.inteceptor.LoginAuth;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.Tag;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.pojo.request.PostGetRequest;
import com.example.forumBackEnd.service.PostService;
import com.example.forumBackEnd.util.TokenUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import com.example.forumBackEnd.pojo.MediaResource;

import java.util.List;

@RestController
@RequestMapping(path="post",produces = "application/json; charset=UTF-8")
@LoginAuth
public class PostController {

    @Resource
    private PostService postService;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping("test")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse httpTest(@RequestBody MediaResource mediaResource) {
        System.out.println(mediaResource.getName());
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setMessage("发送成功");

        basicResponse.setData(mediaResource);

        return basicResponse;
    }

    ;

    @PostMapping("test2")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse httpTest2(@RequestBody List<Tag> tagList) {
        System.out.println(tagList);
        System.out.println(tagList.get(0) != null);
        for (Tag tag : tagList) {
            System.out.println(tag.getName());
        }
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setMessage("发送成功");

        basicResponse.setData(tagList);

        return basicResponse;
    }

    ;

    @PostMapping("add")
    public BasicResponse addPost(@RequestBody ObjectNode request) throws JsonProcessingException {
        String json = request.get("post").toString();
        if (!json.equals("null") && !json.equals("")) {
            Post post = OBJECT_MAPPER.readValue(json, Post.class);
            int postId = postService.addPost(post);
            if (postId > 0) {
                ObjectNode node = OBJECT_MAPPER.createObjectNode();
                node.put("postId", postId);
                return BasicResponse.getSuccessResponse("添加成功", node);
            }
        }
        return BasicResponse.getFailResponse("添加失败");
    }

    @PostMapping("by-last-comment")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse selectPostByLastComment(@RequestBody PostGetRequest request) {
        int offset = request.getOffset();
        List<Post> postList = postService.selectPostByLastComment(offset);
//        for(Post post:postList){
//            System.out.println(post);
//        }
        if (postList.size() == 0) {
            return BasicResponse.getSuccessResponse("获取失败，结果为空。按最近评论，第" + offset + 1 + "页。", null);
        }
        return BasicResponse.getSuccessResponse("按最近评论，第" + offset + 1 + "页",
                postList);
    }

    @PostMapping("by-time")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse selectPostByTime(@RequestBody PostGetRequest request) {
        int offset = request.getOffset();
        List<Post> postList = postService.selectPostByTime(offset);
//        for(Post post:postList){
//            System.out.println(post);
//        }
        return BasicResponse.getSuccessResponse("按最近时间，第" + offset + "页", postList);
    }

    @PostMapping("by-id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse selectPostById(@RequestBody PostGetRequest request) {

        int id = request.getId();
        if (id > 0) {
            Post postList = postService.selectPostById(id);
            if (postList != null) {
                return BasicResponse.getSuccessResponse("按id", postList);
            } else {
                return BasicResponse.getFailResponse("获取消息失败，消息为空");
            }
//        for(Post post:postList){
//            System.out.println(post);
        }return BasicResponse.getFailResponse("获取消息失败");
    }

    @PostMapping("like-by-id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse addLikeById(@RequestBody ObjectNode request){
        int postId = request.get("id").asInt();
        if (postId>0){
            boolean result = postService.addLike(postId);
            if (result){
                return BasicResponse.getSuccessResponse("添加成功",null);
            } else {
                return BasicResponse.getFailResponse("添加失败，帖子不存在");
            }
        } return BasicResponse.getFailResponse("添加失败，帖子id有误");
    }

    @PostMapping("like-cancel-by-id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse cancelLikeById(@RequestBody ObjectNode request){
        int postId = request.get("id").asInt();
        if (postId>0){
            boolean result = postService.cancelLike(postId);
            if (result){
                return BasicResponse.getSuccessResponse("取消成功",null);
            } else {
                return BasicResponse.getFailResponse("取消失败，帖子不存在");
            }
        } return BasicResponse.getFailResponse("取消失败，帖子id有误");
    }
}
