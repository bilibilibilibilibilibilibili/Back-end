package com.example.forumBackEnd.controller;


import cn.hutool.extra.ssh.JschRuntimeException;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.request.PostGetRequest;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="news", produces = "application/json; charset=UTF-8")
public class NewsController {
    @Resource
    private PostService postService;

//    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    @PostMapping("add-news")
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public BasicResponse addNews(@RequestBody ObjectNode request) throws JschRuntimeException{
//        String json = request.get("post").toString();
//        if (!json.equals("null") && !json.equals("")){
//            Post nwes = OBJECT_MAPPER.readValue(json,Post.class);
//            int newsId = postService.addNews(news);
//        }
//    }

    @PostMapping("find-news")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse selectNews(@RequestBody PostGetRequest request) {
        int offset = request.getOffset();
        List<Post> postList = postService.selectNews(offset);
        return BasicResponse.getSuccessResponse("按最新id，第"+offset+"页",postList);
    }

}
