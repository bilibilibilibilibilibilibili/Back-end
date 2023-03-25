package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.pojo.Comment;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.CommentService;
import com.example.forumBackEnd.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="comment",produces = "application/json; charset=UTF-8")
public class CommentController {
    @Resource
    private CommentService commentService;
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping("add")
    public BasicResponse addComment(@RequestBody ObjectNode request) throws Exception {
        String json = request.get("comment").toString();
        if (!json.equals("") && !json.equals("null")){
            Comment comment = OBJECT_MAPPER.readValue(json, Comment.class);
            Boolean result = commentService.addComment(comment);
            if(result){
                return BasicResponse.getSuccessResponse("添加成功",null);
            }
        }
        return BasicResponse.getFailResponse("添加失败，数据不能为空");
    }

    @PostMapping("by-post-id")
    public BasicResponse getCommentByPost(@RequestBody ObjectNode request){
        int postId = request.get("id").asInt();
        int offset = request.get("offset").asInt();
//        System.out.println(postId);
        List<Comment> commentList = commentService.selectCommentByPost(postId,offset);
        if (commentList.size() == 0){
            return BasicResponse.getSuccessResponse("获取失败，帖子不存在或帖子无评论",null);
        }
        return BasicResponse.getSuccessResponse("获取成功，帖子id"+postId+" 第"+offset+1+"页",commentList);
    }

    @PostMapping("by-id")
    public BasicResponse getCommentById(@RequestBody ObjectNode request){
        return BasicResponse.getFailResponse("获取失败");
    }
}
