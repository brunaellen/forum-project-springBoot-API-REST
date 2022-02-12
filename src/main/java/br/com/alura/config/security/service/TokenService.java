package br.com.alura.config.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
  
  @Value(value = "${forum.jwt.expirationTimeMilliseconds}")
  private String expirationTimeMilliseconds;
  
  @Value(value = "${forum.jwt.secret}")
  private String secretKey;

  public String generateToken(Authentication authentication) {
    User userLoggedIn = (User) authentication.getPrincipal();
    Date generationDate = new Date();
    Date expirationDate = new Date(generationDate.getTime() + Long.parseLong(expirationTimeMilliseconds));
    
    return Jwts.builder()
        .setIssuer("API of Alura Forum")
        .setSubject(userLoggedIn.getId().toString())
        .setIssuedAt(generationDate)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getUserId(String token) {
    Claims claimsBody = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
    return Long.parseLong(claimsBody.getSubject());
  }
  
}
