package com.blogproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{
	 List<Comments> findByPostId(long postId);
}
