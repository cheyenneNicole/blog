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
	
	@Id
	@Column(name="entity_id")
	private int entityID;
	
	@Id
	@Column(name="comments_id")
	private int commentnumber;
	
	@Id
	@Column(name="comment")
	private String comment;

	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
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

	public Comment(int entityID, int commentnumber, String comment) {
		this.entityID = entityID;
		this.commentnumber = commentnumber;
		this.comment = comment;
	}

	public Comment() {
	}
	
	
}
