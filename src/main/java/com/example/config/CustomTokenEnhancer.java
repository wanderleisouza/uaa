package com.example.config;

import java.util.LinkedHashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.example.domain.User;


public class CustomTokenEnhancer extends JwtAccessTokenConverter {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		var user = (User) authentication.getPrincipal();
		var info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
		info.put("email", user.getEmail());
		
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		
		return super.enhance(customAccessToken, authentication);
		
	}
	
}
