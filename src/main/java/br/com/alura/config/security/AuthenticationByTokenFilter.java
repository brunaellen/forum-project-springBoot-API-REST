package br.com.alura.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.config.security.service.TokenService;

public class AuthenticationByTokenFilter extends OncePerRequestFilter{
  
  private final String HEADER = "Authorization";
  private final String TYPE = "Bearer ";
  private TokenService tokenService;

  public AuthenticationByTokenFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = recoverToken(request);
    boolean validToken = tokenService.isTokenValid(token);
  }

  private String recoverToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER);
    
    if(token == null || token.isEmpty() || !token.startsWith(TYPE)) {
      return null;
    }
    return token.substring(TYPE.length(), token.length());
  }

}
