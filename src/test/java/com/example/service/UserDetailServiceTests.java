package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.domain.Permission;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.UserDetailRepository;

@SpringBootTest
public class UserDetailServiceTests {

	@Autowired
	UserDetailService userDetailService;

	@MockBean
	UserDetailRepository userDetailRepository;
	
	public User mockUser = new User();

	@BeforeEach
	public void createUser() {
		
		Permission canCreatePermission = new Permission();
		canCreatePermission.setName("can_create");
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleAdmin.setPermissions(List.of(canCreatePermission));
		mockUser.setRoles(List.of(roleAdmin));
		mockUser.setUsername("jane-doe");
		mockUser.setCustomerId("123123");
		mockUser.setDocument("19655085888");
		mockUser.setAccountNonLocked(true);
		mockUser.setAccountNonExpired(true);
		mockUser.setCredentialsNonExpired(true);
		mockUser.setEnabled(true);	
	}
	
	@Test
	public void WhenLoadUserByUsernameReturnUser() {
		Mockito.when(userDetailRepository.findByUsername("jane-doe")).thenReturn(Optional.of(mockUser));
		UserDetails returnedUser = userDetailService.loadUserByUsername("jane-doe");
		assertEquals(mockUser, returnedUser);
	}
	
	@Test
	public void WhenLoadUserByInvalidUsernameThrowsException() {
		Mockito.when(userDetailRepository.findByUsername("jane123")).thenThrow(new UsernameNotFoundException("Wrong username or password"));
		assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername("jane123"));
	}

}
