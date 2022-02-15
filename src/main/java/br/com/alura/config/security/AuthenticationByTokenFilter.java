package br.com.alura.config.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.config.security.service.TokenService;
import br.com.alura.model.User;
import br.com.alura.repository.UserRepository;

public class AuthenticationByTokenFilter extends OncePerRequestFilter{
  
  private static final String HEADER = "Authorization";
  private static final String TYPE = "Bearer ";
  private TokenService tokenService;
  private UserRepository userRepository;

  public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = recoverToken(request);
    boolean validToken = tokenService.isTokenValid(token);
    
    if(validToken) {
      authenticateClient(token);
    }
    
    filterChain.doFilter(request, response);
  }

  private boolean authenticateClient(String token) {
    boolean clientIsAuthenticated = false;
    Long userId = tokenService.getUserId(token);
    User user = userRepository.findById(userId).get();
  
    List<SimpleGrantedAuthority> authorities = user
        .getAuthorities()
        .stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
        .collect(Collectors.toList());
    
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
        authorities);
    
    SecurityContextHolder.getContext().setAuthentication(authentication);
    clientIsAuthenticated = true;
    return clientIsAuthenticated;
  }

  private String recoverToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER);
    
    if(token == null || token.isEmpty() || !token.startsWith(TYPE)) {
      return null;
    } else {
      return token.substring(TYPE.length(), token.length());
    } 
  }

}
