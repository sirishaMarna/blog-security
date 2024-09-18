package com.blogproject.dto;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



public class PostDto {
	private Long id;
	@NotEmpty(message="user name is required")
	@Size(min=5,max=10)
	private String username;
	@NotEmpty(message="title is required")
	private String title;
	@NotEmpty(message="description is required")
	private String description;
	@NotEmpty(message="content is required")
	private String content;
	private Date postDate;
	public PostDto(String username, String title, String description, String content, Date postDate) {
		super();
		this.username = username;
		this.title = title;
		this.description = description;
		this.content = content;
		this.postDate = postDate;
	}
	public PostDto(Long id, String username, String title, String description, String content, Date postDate) {
		super();
		this.id = id;
		this.username = username;
		this.title = title;
		this.description = description;
		this.content = content;
		this.postDate = postDate;
	}
	public PostDto() {
		super();
	}
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", username=" + username + ", title=" + title + ", description=" + description
				+ ", content=" + content + ", postDate=" + postDate + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
