package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Client;
import com.example.exception.ClientNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	ClientDetailsService clientDetailsService;
	
	public Client loadClientByClientIdStripSecret(@PathVariable final String clientId) {
		ClientDetails clientDetails = null;
		try {
			clientDetails = clientDetailsService.loadClientByClientId(clientId);
		} catch (NoSuchClientException e) {
			throw new ClientNotFoundException();
		}
		var wrapper = new Client(clientDetails);
		wrapper.setClientSecret("***");
		return wrapper;
	}
	
}