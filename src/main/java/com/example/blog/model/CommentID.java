package com.example.blog.model;

import java.io.Serializable;

public class CommentID implements Serializable{

	private int entityID;
	private int commentnumber;
	
	public int getEntityID() {
		return entityID;
	}
	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
	public int getCommentnumber() {
		return commentnumber;
	}
	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}
	public CommentID(int entityID, int commentnumber) {
		this.entityID = entityID;
		this.commentnumber = commentnumber;
	}
	public CommentID() {

	}
	
}
