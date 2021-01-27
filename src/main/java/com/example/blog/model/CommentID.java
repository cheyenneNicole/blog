package com.example.blog.model;

import java.io.Serializable;

public class CommentID implements Serializable{

	private int id;
	private int commentnumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommentnumber() {
		return commentnumber;
	}
	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}
	public CommentID(int id, int commentnumber) {
		this.id = id;
		this.commentnumber = commentnumber;
	}
	public CommentID() {

	}
	
}
