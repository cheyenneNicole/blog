package com.example.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.blog.model.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO{

	
	private EntityManager entityManager;
	
	@Autowired
    public CommentDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
	
	@Override
	public List<Comment> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Comment> theQuery = currentSession.createQuery("from Comment", Comment.class);
		List<Comment> comments = theQuery.getResultList();
		return comments;
	}

	@Override
	public Comment findByCommentId(int commentId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Comment> theQuery = currentSession.createQuery("from Comment where commentId=:commentId", Comment.class);
		theQuery.setParameter("commentId", commentId);
		Comment theComment = null;
		try {
			theComment = theQuery.getSingleResult();
		}
		catch(Exception exc) {
			theComment = null;
		}
		return theComment;
	}

	@Override
	public Comment findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Comment theComment = currentSession.get(Comment.class, id);
		return theComment;
	}



	@Override
	public void save(Comment theComment) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theComment);
		
	}

	@Override
	public void deleteByCommentId(int commentNumber) {
		Session currentSession = entityManager.unwrap(Session.class);

        // Delete object with primary key

        Query theQuery = currentSession.createQuery("delete from Comment where commentNumber=:commentNumber");
        theQuery.setParameter("commentNumber", commentNumber);

        theQuery.executeUpdate();
		
	}

}
