package com.example.blog.dao;

import com.example.blog.model.User;

public interface UserDAO {
	  	User findByUserName(String userName);

	    User findByEmail(String theEmail);
	    
	    User save(User user);

	    User findById(Long id);
}
