package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Client;
import com.example.service.ClientService;

@RestController
public class ClientDetailsController {

	@Autowired
	ClientService clientService;

	@GetMapping("clients/{clientId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') and #oauth2.isUser() and #oauth2.hasScope('READ')")
	public Client getByClientId(@PathVariable final String clientId) {
		return clientService.loadClientByClientIdStripSecret(clientId);
	}

}
