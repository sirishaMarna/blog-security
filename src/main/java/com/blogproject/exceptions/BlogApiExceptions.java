package com.blogproject.exceptions;

import org.springframework.http.HttpStatus;

public class BlogApiExceptions extends RuntimeException {
	  private HttpStatus status;
	    private String message;
		public BlogApiExceptions(HttpStatus status, String message) {
			super();
			this.status = status;
			this.message = message;
		}

		public BlogApiExceptions(String message, HttpStatus status, String message1) {
	        super(message);
	        this.status = status;
	        this.message = message1;
	    }

		public HttpStatus getStatus() {
			return status;
		}

		
        @Override
		public String getMessage() {
			return message;
		}

	    
		
	
		
}
