package com.example.blog.service;

import java.util.List;

import com.example.blog.model.BlogPost;

public interface BlogPostService {

	List<BlogPost> get();
	
	BlogPost get(int id);
	
	void save(BlogPost blogPost);
	
	void delete(int id);
}
