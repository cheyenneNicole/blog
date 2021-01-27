package com.example.blog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService theCommentService) {
		commentService = theCommentService;
	}
	
	
	@GetMapping("/comments")
	public List<Comment> findAll(){
		return commentService.findAll();
	}
	
	@GetMapping("/{entityId}/comments")
	public List<Comment> findCommentsById(@PathVariable int id){
		return commentService.findAll()
				.stream()
				.filter(p -> p.getId() == id)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/comments/{commentNumber}")
	public Comment findCommentByCommentNumber (@PathVariable("commentNumber") Integer commentId) {
		return commentService.findByCommentNumber(commentId);
	}
	
	@PostMapping("/comments")
	public Comment addComments(@RequestBody Comment theComment) {
		theComment.setCommentsID(0);
		commentService.save(theComment);
		return theComment;
	}
	
	@DeleteMapping("/comments/{commentNumber}")
	public String deleteComment(@PathVariable int commentNumber) {
		Comment tempComment = commentService.findByCommentNumber(commentNumber);
		
		if(tempComment == null) {
			throw new RuntimeException("Comment number is not found- "+ commentNumber);
		}
		commentService.deleteByCommentNumber(commentNumber);
		
		return "Deleted comment number - " + commentNumber;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
