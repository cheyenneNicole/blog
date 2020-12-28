package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.dao.BlogPostDAO;
import com.example.blog.model.BlogPost;

@Service
public class BlogPostServiceImpl implements BlogPostService{

	@Autowired
	private BlogPostDAO blogPostDAO;
	
	@Transactional
	@Override
	public List<BlogPost> get() {
		return blogPostDAO.get();
	}

	@Transactional
	@Override
	public BlogPost get(int id) {
		return blogPostDAO.get(id);
	}

	@Transactional
	@Override
	public void save(BlogPost blogPost) {
		blogPostDAO.save(blogPost);
	
	}

	@Transactional
	@Override
	public void delete(int id) {
		blogPostDAO.delete(id);
	}

	
}
