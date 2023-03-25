package com.example.forumBackEnd.service;

import com.example.forumBackEnd.mapper.CommentMapper;
import com.example.forumBackEnd.pojo.Comment;
import com.example.forumBackEnd.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Resource
    private PostService postService;
    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private CommentMapper commentMapper;

    public Boolean addComment(Comment comment){
        int affectRow = commentMapper.addComment(comment);
        if (affectRow>0){
            return postService.updatePostOnNewComment(comment.getPostId());
        }
        return false;
    }

    public List<Comment> selectCommentByPost(int postId, int offset){
        List<Comment> commentList = commentMapper.selectCommentByPost(postId, offset);
//        for(Comment comment: commentList){
//            System.out.println(comment);
//        }
        return commentList;
    }

    public Comment selectCommentById(int id){
        return commentMapper.selectCommentById(id);
    }
}
