package com.example.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

@SpringBootTest
class UserDetailRepositoryTest {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Test
	void testFindByUsername() {
		var username = "jane-doe";
		var user = userDetailRepository.findByUsername(username);
		assertEquals(username, user.get().getUsername());
		Collection<? extends GrantedAuthority> authorities = user.get().getAuthorities();
		assertTrue(authorities.toString().contains("ROLE_USER"));
	}

}
