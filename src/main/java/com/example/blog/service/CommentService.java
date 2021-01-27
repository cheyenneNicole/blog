package com.example.blog.service;

import java.util.List;

import com.example.blog.model.Comment;

public interface CommentService {

	public List<Comment> findAll();

    public Comment findByCommentNumber(int commentNumber);

    public Comment findById(int id);

    public void save(Comment theComment);

    public void deleteByCommentNumber(int commentNumber);
}
