package com.example.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.repository.UserDetailRepository;

@Service
public class PasswordService {
	
	private final int PASSWORD_LENGTH = 8;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;

    /**
     * Este método mantém a compatibilidade com o fluxo Magento 
     * Não é seguro gerar strings de senha para envio aos usuários, 
     * este método não deve ser utilizado quando o novo fluxo de 
     * atualizar senha for desenvolvido.
     */
	@Deprecated
	String getRandomPassword() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, PASSWORD_LENGTH);
	}

    /**
     * Este método mantém a compatibilidade com o fluxo Magento 
     * Não é há confirmação de identidade neste fluxo.
     * Este método não deve ser utilizado quando o novo fluxo de 
     * atualizar senha for desenvolvido.
     */
	@Deprecated
	public String reset(String username) {
		var user = userDetailRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		var rawPassowrd = getRandomPassword();
		var encodedPassowrd = passwordEncoder.encode(rawPassowrd);
		user.setPassword(encodedPassowrd);
		userDetailRepository.save(user);
		return rawPassowrd;
	}

}