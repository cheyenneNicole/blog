package com.example.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.blog.model.BlogPost;

@Repository
public class BlogPostDAOImpl implements BlogPostDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<BlogPost> get() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<BlogPost> query = currSession.createQuery("from BlogPost", BlogPost.class);
		List<BlogPost> list = query.getResultList();
		return list;
	}

	@Override
	public BlogPost get(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		BlogPost blogPost = currSession.get(BlogPost.class, id);
		return blogPost;
	}

	@Override
	public void save(BlogPost blogPost) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(blogPost);
	}

	@Override
	public void delete(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		BlogPost blogPost = currSession.get(BlogPost.class, id);
		currSession.delete(blogPost);
		
	}

}
