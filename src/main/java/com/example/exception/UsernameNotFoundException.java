package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Username not found")
public class UsernameNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 910286861232727682L; 
}