package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserDetailRepository;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		var user = userDetailRepository.findByUsername(name)
				.orElseThrow(() -> new UsernameNotFoundException("Wrong username or password"));
		new AccountStatusUserDetailsChecker().check(user);
		return new User(user);
	}

}