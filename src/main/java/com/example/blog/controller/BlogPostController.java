package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;

@RestController
@RequestMapping("/api")
public class BlogPostController {
	
	@Autowired
	private BlogPostService blogPostService;
	
	@CrossOrigin
	@GetMapping("/post")
	public List<BlogPost> get(){
		return blogPostService.get();
	}
	@CrossOrigin
	@PostMapping("/post")
	public BlogPost save(@RequestBody BlogPost blogPost) {
		blogPostService.save(blogPost);
		return blogPost;
	}
	
	@GetMapping("/post/{id}")
	public BlogPost get(@PathVariable int id) {
		return blogPostService.get(id);
	}
	@CrossOrigin
	@DeleteMapping("/post/{id}")
	public String delete(@PathVariable int id) {
		blogPostService.delete(id);
		return "User was removed with id "+id;
	}
	@PutMapping("/post")
	public BlogPost update(@RequestBody BlogPost blogPost) {
		blogPostService.save(blogPost);
		return blogPost;
	}
}
