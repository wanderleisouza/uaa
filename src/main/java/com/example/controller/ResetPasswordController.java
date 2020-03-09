package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PasswordService;


@RestController
public class ResetPasswordController {
	
	@Autowired
	PasswordService passwordService;

    /**
     * Este método mantém a compatibilidade com o fluxo Magento 
     * Não é seguro gerar strings de senha para envio aos usuários, 
     * este método não deve ser utilizado quando o novo fluxo de 
     * atualizar senha for desenvolvido.
     */
	@Deprecated
	@PostMapping("/users/{username}/reset/password")
	@PreAuthorize("#oauth2.isClient() and #oauth2.hasScope('WRITE')")
	public String reset(@PathVariable final String username) {
		return passwordService.reset(username);
	}
	
}
