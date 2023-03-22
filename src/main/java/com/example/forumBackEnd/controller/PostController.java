package com.example.forumBackEnd.controller;


import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.PostDao;
import com.example.forumBackEnd.pojo.Tag;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.pojo.request.PostGetRequest;
import com.example.forumBackEnd.service.PostService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.forumBackEnd.pojo.MediaResource;

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
        int postId = postService.addPost(post);
        if(postId>0){
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("postId",postId);
            return BasicResponse.getSuccessResponse("添加成功",node);
        }
        return  BasicResponse.getFailResponse("添加失败");
    }

    /*
        System.out.println(node);
        Post post = new Post();
        post.setAuthor(node.get("author").asInt());
        post.setTitle(node.get("title").asText());
        post.setContent(node.get("content").asText());
        post.setCommentYorN(node.get("commentYorN").asBoolean());

        ObjectMapper mapper = new ObjectMapper();
        List<String> tag = mapper.convertValue(node.get("tag"), new TypeReference<List<String>>() {
        });
        post.setTag(tag);
        System.out.println(post);
        int postId = postService.addPost(post);
        if (postId >= 0){
            ObjectNode node2 = mapper.createObjectNode();
            node2.put("postId", postId);
            return BasicResponse.getSuccessResponse("添加成功",node2);
        }
        return BasicResponse.getFailResponse("添加失败");
    }*/

    @PostMapping("by-last-comment")
    @JsonIgnoreProperties(ignoreUnknown = true)    // 仅供测试
    public BasicResponse selectPostByLastComment(@RequestBody PostGetRequest request) {
        int offset = request.getOffset();
        List<Post> postList =  postService.selectPostByLastComment(offset);
        for(Post post:postList){
            System.out.println(post);
        }
        return BasicResponse.getSuccessResponse("按最近评论，第"+offset+"页",
                postList);
    }

    @PostMapping("by-time")
    public List<Post> selectPostByTime(int offset) {
        return postService.selectPostByTime(offset);
    }
}
