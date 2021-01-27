package com.example.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.blog.model.User;
import com.example.blog.user.Channel;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(Channel channel);

    UserDetails loadUserById(Long id);
}
