package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.dao.BlogPostDAO;
import com.example.blog.dao.CommentDAO;
import com.example.blog.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	
	private CommentDAO commentDAO;
	
	@Autowired
    public CommentServiceImpl(CommentDAO theCommentDao) {
        commentDAO = theCommentDao;
    }

	
	
	@Override
	@Transactional
	public List<Comment> findAll() {
		return commentDAO.findAll();
	}

	@Override
	@Transactional
	public Comment findByCommentNumber(int commentNumber) {
		return commentDAO.findByCommentId(commentNumber);
	}

	@Override
	@Transactional
	public Comment findById(int id) {
		return commentDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Comment theComment) {
		commentDAO.save(theComment);
		
	}

	@Override
	@Transactional
	public void deleteByCommentNumber(int commentNumber) {
		commentDAO.deleteByCommentId(commentNumber);
		
	}
	

}
