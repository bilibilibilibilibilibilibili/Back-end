package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.inteceptor.LoginAuth;
import com.example.forumBackEnd.service.SearchService;
import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@LoginAuth
@RequestMapping(path="search",produces = "application/json; charset=UTF-8")
@RestController
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping("search-by-name")
    public BasicResponse getSearchByName(@RequestBody ObjectNode request){
        String Name = request.get("Name").asText();
        System.out.println(Name);
        List<String> nameList = searchService.searchByName(Name);
        if (nameList.size() == 0){
            return BasicResponse.getFailResponse("暂无搜索结果，请检查是否输入有误");
        }
        return BasicResponse.getSuccessResponse("获取成功，用户名"+Name,nameList);
    }
}
