package com.example.blog.model;

import java.sql.Date;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="entity_id")
@Table(name="tb_blog")
public class BlogPost  extends com.example.blog.model.Entity{

	
	@Column(name = "title")
	private String title;
	
	@Column(name = "body")
	private String body;

	@Column(name = "likes")
	private boolean likes;
	
	
	public BlogPost() {
	}


	public BlogPost(String title, String body, boolean likes) {
		this.title = title;
		this.body = body;
		this.likes = likes;
	}


	@Override
	public String toString() {
		return "BlogPost [title=" + title + ", body=" + body +likes +"]";
	}
	
	
	public boolean isLikes() {
		return likes;
	}

	public void setLikes(boolean likes) {
		this.likes = likes;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
