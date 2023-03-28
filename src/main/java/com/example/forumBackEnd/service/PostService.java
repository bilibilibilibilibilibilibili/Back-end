package com.example.forumBackEnd.service;


import com.example.forumBackEnd.mapper.PostMapper;
import com.example.forumBackEnd.pojo.Post;
import com.example.forumBackEnd.pojo.PostDao;
import com.example.forumBackEnd.pojo.Tag;
import com.example.forumBackEnd.pojo.enumClass.PostStatus;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @jakarta.annotation.Resource
    private TokenUtil tokenUtil;
    @jakarta.annotation.Resource
    private PostMapper postMapper;


    public int addPost(Post post){
        if (post.getMediaResources() == null){
            post.setMediaResources(new ArrayList<>());
        }
        if (post.getLength() < 0){
            post.setLength(0);
        }
        post.setViews(0);
        post.setLikes(0);
        post.setCollections(0);
        post.setComment(0);
        post.setStatus(PostStatus.WAIT);

        int affectRows =  postMapper.addPost(post);
        if(affectRows <= 0){
            return 0;
        }
        return post.getId();
    }

    public List<Post> selectPostByLastComment(int offset)  {
        List<Post> postList = postMapper.selectPostByLastComment(offset);
//        for(Post post:postList){
//            System.out.println(post);
//        }
        return postList;
    }

    public List<Post> selectPostByTime(int offset) {
        List<Post> postList = postMapper.selectPostByTime(offset);
        for (Post post:postList){
            System.out.println(post);
        }
        return postMapper.selectPostByTime(offset);
    }

    public Boolean updatePostOnNewComment(int postId){
        int affectRows =  postMapper.updatePostOnNewComment(postId);
        return affectRows>0;
    }

    public List<Post> selectPostById(int offset) {
        List<Post> postList = postMapper.selectPostById(offset);
        return postList;
    }

    public int selectCommentFloorByPostId(int postId) {
        return postMapper.selectCommentFloorByPostId(postId);
    }

}
