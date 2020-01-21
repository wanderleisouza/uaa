package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final char[] KEY_STORE_PASSWORD = "password".toCharArray();
    
    @Bean
    TokenStore jdbcTokenStore() {
        //return new JdbcTokenStore(dataSource);
    	return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.tokenStore(jdbcTokenStore());
    	endpoints.tokenStore(jdbcTokenStore()).tokenEnhancer(jwtAccessTokenConverter());
        endpoints.authenticationManager(authenticationManager);
    }
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() { 
    	JwtAccessTokenConverter jwtAccessTokenConverter = new CustomTokenEnhancer();
    	jwtAccessTokenConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), KEY_STORE_PASSWORD).getKeyPair("jwt"));
    	return jwtAccessTokenConverter;
    }
    
}