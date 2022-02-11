package br.com.alura.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.model.User;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {
  
  @Value(value = "${forum.jwt.expirationTimeMilliseconds}")
  private String expirationTimeMilliseconds;

  public String generateToken(Authentication authentication) {
    User userLoggedIn = (User) authentication.getPrincipal();
    Date generationDate = new Date();
    Date expirationDate = new Date(generationDate.getTime() + Long.parseLong(expirationTimeMilliseconds));
    
    return Jwts.builder()
        .setIssuer("API of Alura Forum")
        .setSubject(userLoggedIn.getId().toString())
        .setIssuedAt(generationDate)
        .setExpiration(expirationDate);
  }
  
}
