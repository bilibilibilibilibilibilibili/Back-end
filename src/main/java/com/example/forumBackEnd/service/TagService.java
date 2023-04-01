package com.example.forumBackEnd.service;

import com.example.forumBackEnd.mapper.TagMapper;
import com.example.forumBackEnd.pojo.Tag;
import com.example.forumBackEnd.pojo.enumClass.TagStatus;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Resource
    private TagMapper tagMapper;

    public int addTag(String name){
        Tag tag = new Tag(name, TagStatus.NORMAL);
        int affectRows= tagMapper.insertTag(tag);
        return affectRows;
    }
}
