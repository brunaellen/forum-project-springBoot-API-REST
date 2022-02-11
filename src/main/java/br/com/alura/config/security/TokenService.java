package br.com.alura.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

  public String generateToken(Authentication authentication) {
    return Jwts.builder()
        .setIssuer("API of Alura Forum");
  }
  
}
