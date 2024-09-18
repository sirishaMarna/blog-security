package com.blogproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class TitleExistsException extends RuntimeException{
	
	private String title;
	
	private String expMsg;

	public TitleExistsException(String title, String expMsg) {
		super();
		this.title = title;
		this.expMsg = expMsg;
	}

	public TitleExistsException() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExpMsg() {
		return expMsg;
	}

	public void setExpMsg(String expMsg) {
		this.expMsg = expMsg;
	}

	
	
	
	

}
