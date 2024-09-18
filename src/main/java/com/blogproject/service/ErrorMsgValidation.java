package com.blogproject.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMsgValidation {


	public ResponseEntity<Map<String, String>> getBindingResultErrors(BindingResult errorResults) {
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldErrro : errorResults.getFieldErrors()) {
			errorMap.put(fieldErrro.getField(), fieldErrro.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
}
