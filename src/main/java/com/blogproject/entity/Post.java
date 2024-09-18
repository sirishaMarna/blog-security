package com.blogproject.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	@Column(unique = true)
	private String title;
	private String description;
	private String content;
	private Date postDate;
	
	  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Comments> comments = new HashSet<>();

	public Post(Long id, String username, String title, String description, String content, Date postDate,
			Set<Comments> comments) {
		super();
		this.id = id;
		this.username = username;
		this.title = title;
		this.description = description;
		this.content = content;
		this.postDate = postDate;
		this.comments = comments;
	}

	public Post(String username, String title, String description, String content, Date postDate,
			Set<Comments> comments) {
		super();
		this.username = username;
		this.title = title;
		this.description = description;
		this.content = content;
		this.postDate = postDate;
		this.comments = comments;
	}

	public Post() {
		super();
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

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", username=" + username + ", title=" + title + ", description=" + description
				+ ", content=" + content + ", postDate=" + postDate + ", comments=" + comments + "]";
	}
	  
	
	
	
	

}
