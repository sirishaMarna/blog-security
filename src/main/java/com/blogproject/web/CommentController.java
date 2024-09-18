package com.blogproject.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogproject.dto.CommentsDto;
import com.blogproject.service.CommentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	 private CommentsService commentService;

	    public CommentController(CommentsService commentService) {
	        this.commentService = commentService;
	    }

	    @PostMapping("/posts/{Id}/comments")
	    public ResponseEntity<CommentsDto> createComment(@PathVariable(value = "Id") long Id,
	                                                    @RequestBody CommentsDto commentDto){
	        return new ResponseEntity<>(commentService.createComment(Id, commentDto), HttpStatus.CREATED);
	    }

	    @GetMapping("/posts/{postId}/comments")
	    public List<CommentsDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
	        return commentService.getCommentsByPostId(postId);
	    }

	    @GetMapping("/posts/{postId}/comments/{id}")
	    public ResponseEntity<CommentsDto> getCommentById(@PathVariable(value = "postId") Long postId,
	                                                     @PathVariable(value = "id") Long commentId){
	        CommentsDto commentDto = commentService.getCommentById(postId, commentId);
	        return new ResponseEntity<>(commentDto, HttpStatus.OK);
	    }

	    @PutMapping("/posts/{postId}/comments/{id}")
	    public ResponseEntity<CommentsDto> updateComment(@PathVariable(value = "postId") Long postId,
	                                                    @PathVariable(value = "id") Long commentId,
	                                                    @Valid @RequestBody CommentsDto commentDto){
	        CommentsDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
	        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	    }

	    @DeleteMapping("/posts/{postId}/comments/{id}")
	    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
	                                                @PathVariable(value = "id") Long commentId){
	        commentService.deleteComment(postId, commentId);
	        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	    }
}
