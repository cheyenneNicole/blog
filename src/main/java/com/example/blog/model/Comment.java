package com.example.blog.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@IdClass(CommentID.class)
@Table(name= "tb_comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="entity_id")
	private int id;
	
	@Id
	@Column(name="comments_id")
	private int commentnumber;
	
	@Column(name="comment")
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentsID() {
		return commentnumber;
	}

	public void setCommentsID(int commentnumber) {
		this.commentnumber = commentnumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment(int id, int commentnumber, String comment) {
		this.id = id;
		this.commentnumber = commentnumber;
		this.comment = comment;
	}

	public Comment() {
	}
	
	
}
