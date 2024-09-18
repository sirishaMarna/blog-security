package com.blogproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
