package com.example.blog.dao;

import java.util.List;

import com.example.blog.model.Comment;

public interface CommentDAO {

	 List<Comment> findAll();
	
	 Comment findByCommentId(int commentId);
	
	 Comment findByEntityId(int entityId);
	
	 void save(Comment theComment);
	
	 void deleteByCommentId(int commentNumber);
}
