package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.request.PostGetRequest;
import com.example.forumBackEnd.service.SearchService;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="search",produces = "application/json; charset=UTF-8")
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping("search-by-name")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public BasicResponse getSearchByName(@RequestBody ObjectNode request){
        String userName = request.get("userName").asText();
        List<User> nameList = searchService.searchByName(userName);
        if (nameList.size() == 0){
            return BasicResponse.getSuccessResponse("暂无搜索结果，请检查是否输入有误", null);
        }
        return BasicResponse.getSuccessResponse("获取成功，用户名"+userName,nameList);
    }

    @PostMapping("search-by-tag")
    public BasicResponse getSearchByTag(@RequestBody ObjectNode request){
        String Tag = request.get("tag").asText();
        List<Post> postList = searchService.serchByTag(Tag);
        if (postList.size() == 0){
            return BasicResponse.getSuccessResponse("无搜索结果，请重新输入", null);
        }
        return BasicResponse.getSuccessResponse("按tag检索", postList);
    }

    @PostMapping("search-by-title")
    public BasicResponse getSearchByTitle(@RequestBody ObjectNode request){
        String Title = request.get("title").asText();
        List<Post> postList = searchService.searchByTitle(Title);
        if (postList.size() == 0){
            return BasicResponse.getSuccessResponse("无搜索结果，请重新输入", null);
        }
        return BasicResponse.getSuccessResponse("按标题检索", postList);
    }
}
