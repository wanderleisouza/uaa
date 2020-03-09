package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.repository.UserDetailRepository;

@SpringBootTest
class PasswordServiceTest {

	@Autowired
	PasswordService passwordService;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Test
	void testGetRandomPasswordLenght() {
		int expectedPasswordLength = 8;
		var password = passwordService.getRandomPassword();
		assertEquals(expectedPasswordLength, password.length());
	}
	
	@Test
	void testGetRandomPassword() {
		var pass1 = passwordService.getRandomPassword();
		var pass2 = passwordService.getRandomPassword();
		assertFalse(pass1.equals(pass2));
	}
	
	@Test 
	void testResetPasswordChange() { 
		var username = "dory-tang";
		var user = userDetailRepository.findByUsername(username);
		var currentPassword = user.get().getPassword();
		var returnedNewPassord = passwordService.reset(username);
		user = userDetailRepository.findByUsername(username);
		var newPassword = user.get().getPassword();
		assertFalse(currentPassword.equals(newPassword));
	}
	
	@Test 
	void testResetPasswordEncrypted() { 
		var encryptMethod = "{bcrypt}";
		var username = "dory-tang";
		var user = userDetailRepository.findByUsername(username);
		var returnedNewPassord = passwordService.reset(username);
		user = userDetailRepository.findByUsername(username);
		var newPassword = user.get().getPassword();
		assertFalse(returnedNewPassord.equals(newPassword));
		assertTrue(newPassword.contains(encryptMethod));
		assertFalse(returnedNewPassord.contains(encryptMethod));
	}

	@Test
	void testResetPasswordWhenUserNotFound() { 
		var username = "somebody-that-does-not-exist";
		assertThrows(UsernameNotFoundException.class, () -> passwordService.reset(username));
	}
	
}
