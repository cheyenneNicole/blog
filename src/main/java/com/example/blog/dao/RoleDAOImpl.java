package com.example.blog.dao;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.blog.model.Role;

@Repository
	public class RoleDAOImpl implements RoleDAO {

		// Define field for entitymanager

		private EntityManager entityManager;

		// Set up constructor injection

		@Autowired
		public RoleDAOImpl(EntityManager theEntityManager) {
			entityManager = theEntityManager;
		}

		@Override
		public Role findRoleByName(String theRoleName) {
			// Get the current hibernate session

			Session currentSession = entityManager.unwrap(Session.class);

			// now retrieve/read from database using name
			Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
			theQuery.setParameter("roleName", theRoleName);
			
			Role theRole = null;
			
			try {
				theRole = theQuery.getSingleResult();
			} catch (Exception e) {
				theRole = null;
			}
			
			return theRole;
		}
}
