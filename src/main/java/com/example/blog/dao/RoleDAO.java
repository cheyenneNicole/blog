package com.example.blog.dao;

import com.example.blog.model.Role;

public interface RoleDAO {

	public Role findRoleByName(String theRoleName);
	
}
