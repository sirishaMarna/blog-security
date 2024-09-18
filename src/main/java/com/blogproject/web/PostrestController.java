package com.blogproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogproject.dto.PagningAndSortingResponse;
import com.blogproject.dto.PostDto;
import com.blogproject.service.ErrorMsgValidation;
import com.blogproject.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PostrestController {
	@Autowired
	public PostService psv;
	
	@Autowired
	public ErrorMsgValidation errorMsgs;
	
	@PostMapping("/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> sendthepost(@Valid @RequestBody PostDto dto,BindingResult errorResults){
		if(errorResults.hasErrors()) {
			return errorMsgs.getBindingResultErrors(errorResults);
		}else {			
		return new ResponseEntity<PostDto>(psv.sendpost(dto),HttpStatus.CREATED);
	}
	}
	
	@GetMapping("/posts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PagningAndSortingResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {
		return new ResponseEntity<PagningAndSortingResponse>(psv.getAllPosts(pageNo,pageSize,sortBy,sortDir), HttpStatus.OK);

	}
	
//	@GetMapping("/getAllCountries")
//	public ResponseEntity<String> getAllCountries(){
//		
//		String countries=psv.getAllCountries();
//		return new ResponseEntity<String>(countries, HttpStatus.OK);
//		
//	}
//
//	@GetMapping("/getAllCountries")
//	public ResponseEntity<String> getAllAuthors(){
//		
//		String authors=psv.getAllauthors();
//		return new ResponseEntity<String>(authors, HttpStatus.OK);
//		
//	}
	
	@GetMapping("/posts/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> getallpostsbyid(@PathVariable Long id){
	return new ResponseEntity<PostDto>(psv.getDetailsById(id),HttpStatus.OK);
		
	}
	@DeleteMapping("/posts/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteByid(@PathVariable Long id){
    psv.deleteDetailsById(id);
	}
//	
//	@PutMapping("/posts/{id}")
//	public ResponseEntity<PostDto> updatethepost(@RequestBody PostDto dto,@PathVariable("id") Long id ){
//		dto.setId(id);
//		PostDto updateuserpost=psv.updatedata(dto, id);
//		return new ResponseEntity<PostDto>(updateuserpost,HttpStatus.OK);
//		
//	}
	
	@PutMapping("/posts/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> updateDetailsById(@PathVariable long id, @RequestBody PostDto dto) {
		PostDto updatedPost = psv.updateDetailsById(id, dto);

		if (updatedPost == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedPost);
	}
	
	

}
