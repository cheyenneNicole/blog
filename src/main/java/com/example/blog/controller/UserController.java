package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.blog.model.User;
import com.example.blog.payload.UserSummary;
import com.example.blog.security.CurrentUser;

import com.example.blog.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	    private UserService userService;

	    @Autowired
	    public UserController(UserService theUserService) {
	        userService = theUserService;
	    }

	    // Add mapping for getting the current user

	    @GetMapping("/user/me")
	    @PreAuthorize("hasRole('USER')")
	    public UserSummary getCurrentUser(@CurrentUser UserDetails userDetails) {

	        User currentUser = userService.findByUserName(userDetails.getUsername());

	        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUserName(),
	                                                currentUser.getFirstName()+ " " + currentUser.getLastName());
	        return userSummary;
	    }

	    // Add mapping for GET /videos/{videoId}

	    @GetMapping("/users/{userName}")
	    public User getUser(@PathVariable String userName) {

	        User theUser = userService.findByUserName(userName);

	        if (theUser == null) {
	            throw new RuntimeException("Username is not found - " + userName);
	        }

	        return theUser;

	    }


}