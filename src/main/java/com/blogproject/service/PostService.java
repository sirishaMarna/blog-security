package com.blogproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blogproject.dto.PagningAndSortingResponse;
import com.blogproject.dto.PostDto;
import com.blogproject.entity.Post;
import com.blogproject.exceptions.TitleExistsException;
import com.blogproject.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	public PostRepository psr;
	
	@Autowired
	public ModelMapper modelmapper;
	
	@Autowired
	public RestTemplate restTemplate;
	
	public PostDto sendpost(PostDto dto) {
		Post post=this.modelmapper.map(dto,Post.class);
		try {
		post=psr.save(post);
		}catch(Exception e) {
			e.getMessage();
			throw new TitleExistsException(dto.getTitle(), "Already "+dto.getTitle()+" Existed");
		}
		dto=this.modelmapper.map(post, PostDto.class);
		return dto;	
	}
	
	public PagningAndSortingResponse getAllPosts(int pageNo,int size,String sortBy,String sortDir){
		List<PostDto> dtoList = new ArrayList<>();
	      Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();
	      
	      Pageable pageable = PageRequest.of(pageNo,size,sort);
			Page<Post>  postList  =psr.findAll(pageable);

		for (Post post : postList) {
			PostDto dto = new PostDto();
			dto = this.modelmapper.map(post, PostDto.class);
			dtoList.add(dto);
		}

		PagningAndSortingResponse pagningAndSortingResponse =new PagningAndSortingResponse();
		pagningAndSortingResponse.setContent(dtoList);
		pagningAndSortingResponse.setLast(postList.isLast());
		pagningAndSortingResponse.setPageSize(postList.getSize());
		pagningAndSortingResponse.setTotalElements(postList.getTotalElements());
		pagningAndSortingResponse.setPageNo(postList.getNumber());
		pagningAndSortingResponse.setTotalPages(postList.getTotalPages());
		return pagningAndSortingResponse;
	}

public String getAllCountries() {
		
		String countries = restTemplate.getForObject("https://restcountries.com/v3.1/all", String.class);
	return countries;
	}


public String getAllauthors() {
		
		String countries = restTemplate.getForObject("https://localhost:8082/authors", String.class);
	return countries;
	}
	
	
	public List<PostDto>getallposts(){
		List<PostDto> dtolist=new ArrayList<>();		
		List<Post> postlist=psr.findAll();
		for(Post post:postlist) {
			PostDto dto=new PostDto();
			dto=this.modelmapper.map(post,PostDto.class);
			dtolist.add(dto);
		}
		
		return dtolist;
	}
//	
//	public PostDto getallpostsbyId(Long id) {
//		
//		Post post =this.modelmapper.map(id, Post.class);
//		post=psr.findById(id).get();		
//			return this.modelmapper.map(post, PostDto.class);
//	}
	public PostDto getDetailsById(long id) {

		Post post = psr.findById(id).orElseThrow();

		return this.modelmapper.map(post, PostDto.class);

	}
//	public void deletepostbyid(Long id) {
//		Post post =this.modelmapper.map(id, Post.class);
//		post=psr.findById(id).get();
//		this.psr.delete(post);
//	//	this.modelmapper.map(post, PostDto.class);
//	}
	
	public void deleteDetailsById(long id) {

		psr.deleteById(id);

	}
	
//	public PostDto updatedata(PostDto dto,Long id) {
//		Post post=this.modelmapper.map(dto, Post.class);
//		post=psr.findById(id).get();
//		if(dto.getUsername()!=null) {
//			post.setUsername(dto.getUsername());
//		}if(dto.getTitle()!=null) {
//			post.setTitle(dto.getTitle());
//		}if(dto.getContent()!=null) {
//			post.setContent(dto.getContent());
//		}if(dto.getDescription()!=null) {
//			post.setDescription(dto.getDescription());
//		}if(dto.getPostDate()!=null) {
//			post.setPostDate(dto.getPostDate());
//		}
//		Post updated=this.psr.save(post);
//		return this.modelmapper.map(updated, PostDto.class);
//		
//		
	
	
	public PostDto updateDetailsById(long id, PostDto updatedPostDto) {

		Optional<Post> optionalPost = psr.findById(id);
		if (!optionalPost.isPresent()) {
			return null;
		}
		Post existingPost = optionalPost.get();

		existingPost.setTitle(updatedPostDto.getTitle());
		existingPost.setContent(updatedPostDto.getContent());
		Post updatedPost = psr.save(existingPost);

		return modelmapper.map(updatedPost, PostDto.class);
	}
	
	
	
	
}
