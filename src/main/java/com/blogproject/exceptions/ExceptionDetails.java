package com.blogproject.exceptions;

import java.util.Date;

public class ExceptionDetails {

	private Date timeStamp;
	private String feild;
	private String message;
	
	public ExceptionDetails(Date timeStamp, String feild, String message) {
		super();
		this.timeStamp = timeStamp;
		this.feild = feild;
		this.message = message;
	}

	public ExceptionDetails() {
		super();
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFeild() {
		return feild;
	}

	public void setFeild(String feild) {
		this.feild = feild;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
