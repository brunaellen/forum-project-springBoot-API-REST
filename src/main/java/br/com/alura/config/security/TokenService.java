package br.com.alura.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.model.User;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

  public String generateToken(Authentication authentication) {
    User userLoggedIn = (User) authentication.getPrincipal();
    
    return Jwts.builder()
        .setIssuer("API of Alura Forum")
        .setSubject(userLoggedIn.getId().toString());
  }
  
}
