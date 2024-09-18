package com.blogproject.web;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blogproject.exceptions.ExceptionDetails;
import com.blogproject.exceptions.TitleExistsException;

@ControllerAdvice
public class BlogExceptionRestController {
	
	@ExceptionHandler(value = TitleExistsException.class)
	public ResponseEntity<ExceptionDetails> exception(TitleExistsException exception) {
		ExceptionDetails exceptiondetails = new ExceptionDetails(new Date(),exception.getTitle(),exception.getExpMsg());
		return new ResponseEntity<ExceptionDetails>(exceptiondetails, HttpStatus.NOT_FOUND);
	}

}
