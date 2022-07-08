package com.assignment.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;

	public AssignmentException() {
		super();
	}

	public AssignmentException(HttpStatus code, String message) {
		super(message);
		this.statusCode = code;
	}

}
