package com.example.blog;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import com.example.blog.dao.BlogPostDAO;
import com.example.blog.dao.CommentDAO;
import com.example.blog.model.BlogPost;
import com.example.blog.model.Comment;
import com.example.blog.service.BlogPostService;
import static org.mockito.ArgumentMatchers.any;
import com.example.blog.service.CommentService;

@SpringBootTest
public class TestService{
	
	@Autowired
	private BlogPostService service;
	
	@Autowired
	private CommentService commentService;
	
	@MockBean
	private BlogPostDAO dao;
	
	@MockBean
	private CommentDAO commentDAO;
	
	@Test
	@DisplayName("Test findALL success")
	void testFindAll() {
		BlogPost blogPost1 = new BlogPost("TEST1", "TestBody1", true);
		BlogPost blogPost2 = new BlogPost("TEST2", "TestBody2", false);
		BlogPost blogPost3 = new BlogPost("TEST3", "TestBody3", true);
		doReturn(Arrays.asList(blogPost1, blogPost2, blogPost3)).when(dao).get();
		
		List<BlogPost> posts = service.get();
		
		Assertions.assertEquals(3, posts.size(), "Findall should return 3");
	}
	
	@Test
	@DisplayName("Test findAllComments success")
	void testFindAllComments() {
		Comment comment1 = new Comment(1, 1, "TEST1");
		Comment comment2 = new Comment(1, 2, "TEST2");
		Comment comment3 = new Comment(2, 3, "TEST3");
		doReturn(Arrays.asList(comment1, comment2, comment3)).when(commentDAO).findAll();
		
		List<Comment> comments = commentService.findAll();
		Assertions.assertEquals(3, comments.size(), "Find all comments should return 3");
	}

	@Test
	@DisplayName("Test for saving post")
	void testSavePost() {
		BlogPost post = new BlogPost("SAVING NEW" , "TEST", false);
		doNothing().when(dao).save(any());
		service.save(post);
		verify(dao).save(post);
	}
	
	@Test
	@DisplayName("Test for saving comment")
	void testSaveComment() {
		Comment comment = new Comment(2, 4, "This is a lamee");
		doNothing().when(commentDAO).save(any());
		commentService.save(comment);
		verify(commentDAO).save(comment);
	}
	
	
}
