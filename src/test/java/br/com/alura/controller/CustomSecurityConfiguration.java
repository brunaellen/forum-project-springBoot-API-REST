package br.com.alura.controller;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.config.security.service.AuthenticationService;
import br.com.alura.config.security.service.TokenService;
import br.com.alura.repository.UserRepository;

@Configuration
public class CustomSecurityConfiguration {

  @MockBean
  private AuthenticationService authenticationService;
  
  @MockBean
  private TokenService tokenService;
  
  @MockBean
  private UserRepository userRepository;
  
}
