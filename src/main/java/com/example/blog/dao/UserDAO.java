package com.example.blog.dao;

import java.util.List;

import com.example.blog.model.User;


public interface UserDAO {

	List<User> get();
	
	User get(int id);
	
	void save(User user);
	
	void delete(int id);
}
