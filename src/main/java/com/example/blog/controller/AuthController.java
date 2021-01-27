package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.dao.RoleDAO;
import com.example.blog.dao.UserDAO;
import com.example.blog.exception.AppException;
import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.JwtAuthenticationResponse;
import com.example.blog.payload.LoginRequest;
import com.example.blog.payload.SignUpRequest;
import com.example.blog.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDAO userRepository;

    @Autowired
    RoleDAO roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	System.out.println("In signin");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		System.out.println("In signup");
		if(userRepository.findByUserName(signUpRequest.getUsername()) != null) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
				 HttpStatus.BAD_REQUEST);
		}
		if(userRepository.findByEmail(signUpRequest.getEmail()) != null) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
	                   HttpStatus.BAD_REQUEST);
	    }

        // Creating user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword(),
				signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail());
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       Role userRole = roleRepository.findRoleByName("USER");

       if (userRole == null) {
         throw new AppException("User Role not set.");
       }

       user.setRoles(Collections.singleton(userRole));
       User result = userRepository.save(user);
       URI location = ServletUriComponentsBuilder
               .fromCurrentContextPath().path("/api/users/{username}")
		.buildAndExpand(result.getUserName()).toUri();
       return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
}
}
