package com.example.forumBackEnd.controller;

import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.TagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(path = "tag", produces = "application/json; charset=UTF-8")
public class TagController {
    @Resource
    private TagService tagService;
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping("add")
    public BasicResponse addTag(@RequestBody ObjectNode request){
        String name = request.get("name").toString();
        if(!name.equals("")){
            int affectRows = tagService.addTag(name);
            if (affectRows > 0){
                return BasicResponse.getSuccessResponse("添加成功", null);
            }
        }
        return BasicResponse.getFailResponse("添加Tag失败，tag名称不能为空");
    }

    @PostMapping("get-hit-tags")
    public BasicResponse getHitTags(@RequestBody ObjectNode request){

        return BasicResponse.getFailResponse("获取热门tag失败");
    }

    @PostMapping("get-tag-by-name")
    public BasicResponse getTagsByName(@RequestBody ObjectNode request){

        return BasicResponse.getFailResponse("获取热门tag失败");
    }
}
