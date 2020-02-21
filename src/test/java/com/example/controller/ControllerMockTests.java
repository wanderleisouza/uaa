package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerMockTests {

	@Autowired
	private MockMvc mockMvc;
	
	private String accessToken = new String();
	private String refreshToken = new String();


	public void generateAccessTokenAndRefreshToken() throws Exception {
		MvcResult result = mockMvc.perform(post("/oauth/token").param("grant_type", "password")
				.param("username", "jane-doe")
				.param("password", "jane-doe123")
				.param("scope", "READ")
				.contentType("application/json"))
				.andReturn();
	String content = result.getResponse().getContentAsString();
	JSONObject obj = new JSONObject(content);
    accessToken = obj.getString("access_token");
    refreshToken = obj.getString("refresh_token");
	}
	
	@Test
	@WithMockUser(username = "app-mobile", password = "app-mobile123")
	public void testGrantTypePassword() throws Exception {
		mockMvc.perform(post("/oauth/token").param("grant_type", "password")
				.param("username", "jane-doe")
				.param("password", "jane-doe123")
				.param("scope", "READ")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "app-mobile", password = "app-mobile123")
	public void testCheckToken() throws Exception {
		generateAccessTokenAndRefreshToken();
		MvcResult checkTokenResult = mockMvc.perform(get("/oauth/check_token")
				.param("token", accessToken)
				.contentType("application/json"))
				.andReturn();
		String content = checkTokenResult.getResponse().getContentAsString();
		JSONObject obj = new JSONObject(content);
	    boolean tokenActive = obj.getBoolean("active");
	    assertEquals(true, tokenActive);
	}
	

	@Test
	@WithMockUser(username = "app-mobile", password = "app-mobile123")
	public void testClientCredentials() throws Exception {
		mockMvc.perform(post("/oauth/token")
				.param("grant_type", "client_credentials")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	

	

}
