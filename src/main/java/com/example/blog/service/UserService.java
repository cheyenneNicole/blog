package com.example.blog.service;

import java.util.List;

import com.example.blog.model.User;

public interface UserService {

List<User> get();
	
	User get(int id);
	
	void save(User user);
	
	void delete(int id);
}
