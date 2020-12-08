package com.example.blog.dao;

import java.util.List;

import com.example.blog.model.BlogPost;


public interface BlogPostDAO {

	List<BlogPost> get();
	
	BlogPost get(int id);
	
	void save(BlogPost blogPost);
	
	void delete(int id);
}
