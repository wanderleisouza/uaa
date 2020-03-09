package com.example.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client implements ClientDetails {
	
	private static final long serialVersionUID = -966823708694890877L;
	
    public Client(ClientDetails client) {
    	this.accessTokenValiditySeconds = client.getAccessTokenValiditySeconds();
    	this.authorizedGrantTypes = client.getAuthorizedGrantTypes();
    	this.clientId = client.getClientId();
    	this.clientSecret = client.getClientSecret();
    	this.refreshTokenValiditySeconds = client.getRefreshTokenValiditySeconds();
    	this.registeredRedirectUri = client.getRegisteredRedirectUri();
    	this.resourceIds = client.getResourceIds();
    	this.scope = client.getScope();
    }

	private String clientId;
	private Set<String> resourceIds;
	@JsonIgnore
	private boolean secretRequired;
	private String clientSecret;
	@JsonIgnore
	private boolean scoped;
	private Set<String> scope;
	private Set<String> authorizedGrantTypes;
	private Set<String> registeredRedirectUri;
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;
	@JsonIgnore
	private Map<String, Object> additionalInformation;
	
	@Override
	@JsonIgnore
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {
		return null;
	}
	
}