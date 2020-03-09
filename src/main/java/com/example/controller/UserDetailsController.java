package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.service.UserDetailService;
	
@RestController
public class UserDetailsController {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailService userDetailService;
	
	@GetMapping("users/{username}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER_HAPPINESS') and #oauth2.isUser() and #oauth2.hasScope('READ')")
	public User getByUsername(@PathVariable final String username) {
		return userDetailService.loadUserByUsernameStripPassword(username);
	}
	
}
