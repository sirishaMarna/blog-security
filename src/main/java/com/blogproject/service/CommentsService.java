package com.blogproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blogproject.dto.CommentsDto;
import com.blogproject.entity.Comments;
import com.blogproject.entity.Post;
import com.blogproject.exceptions.BlogApiExceptions;
import com.blogproject.exceptions.ResourceNotFoundException;
import com.blogproject.repository.CommentRepository;
import com.blogproject.repository.PostRepository;


@Service
public class CommentsService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	   private ModelMapper mapper;
	
	    public CommentsDto createComment(Long postId, CommentsDto commentDto) {

	        Comments comment = mapToEntity(commentDto);
	        Post post = postRepository.findById(postId).orElseThrow(
	                () -> new ResourceNotFoundException("Post", "id", postId));
	        comment.setPost(post);
	        Comments newComment =  commentRepository.save(comment);
	        return mapToDTO(newComment);
	    }
	    
	    
	    public List<CommentsDto> getCommentsByPostId(long postId) {
	        List<Comments> comments = commentRepository.findByPostId(postId);
	        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
	    }

	   
	    public CommentsDto getCommentById(Long postId, Long commentId) {
	        Post post = postRepository.findById(postId).orElseThrow(
	                () -> new ResourceNotFoundException("Post", "id", postId));
	        Comments comment = commentRepository.findById(commentId).orElseThrow(() ->
	                new ResourceNotFoundException("Comment", "id", commentId));

	        if(!comment.getPost().getId().equals(post.getId())){
	            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
	        }

	        return mapToDTO(comment);
	    }

	   
	    public CommentsDto updateComment(Long postId, long commentId, CommentsDto commentRequest) {
	        Post post = postRepository.findById(postId).orElseThrow(
	                () -> new ResourceNotFoundException("Post", "id", postId));
	        Comments comment = commentRepository.findById(commentId).orElseThrow(() ->
	                new ResourceNotFoundException("Comment", "id", commentId));

	        if(!comment.getPost().getId().equals(post.getId())){
	            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
	        }

	        comment.setName(commentRequest.getName());
	        comment.setEmail(commentRequest.getEmail());
	        comment.setBody(commentRequest.getBody());

	        Comments updatedComment = commentRepository.save(comment);
	        return mapToDTO(updatedComment);
	    }

	    public void deleteComment(Long postId, Long commentId) {
	        Post post = postRepository.findById(postId).orElseThrow(
	                () -> new ResourceNotFoundException("Post", "id", postId));
	        Comments comment = commentRepository.findById(commentId).orElseThrow(() ->
	                new ResourceNotFoundException("Comment", "id", commentId));

	        if(!comment.getPost().getId().equals(post.getId())){
	            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
	        }

	        commentRepository.delete(comment);
	    }
    
	  private CommentsDto mapToDTO(Comments comment){
	        CommentsDto commentDto = mapper.map(comment, CommentsDto.class);

//	        CommentDto commentDto = new CommentDto();
//	        commentDto.setId(comment.getId());
//	        commentDto.setName(comment.getName());
//	        commentDto.setEmail(comment.getEmail());
//	        commentDto.setBody(comment.getBody());
	        return  commentDto;
	    }

	    private Comments mapToEntity(CommentsDto commentDto){
	        Comments comment = mapper.map(commentDto, Comments.class);
//	        Comment comment = new Comment();
//	        comment.setId(commentDto.getId());
//	        comment.setName(commentDto.getName());
//	        comment.setEmail(commentDto.getEmail());
//	        comment.setBody(commentDto.getBody());
	        return  comment;
	    }
}
