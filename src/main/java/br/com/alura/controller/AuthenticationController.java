package br.com.alura.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.config.security.TokenService;
import br.com.alura.controller.dto.LoginFormDto;
import br.com.alura.controller.dto.TokenDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager authManager;
  
  @Autowired
  private TokenService tokenService;
  
  @PostMapping
  public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginFormDto form) {
    UsernamePasswordAuthenticationToken loginDetails = new UsernamePasswordAuthenticationToken(form.getEmail(),
        form.getPassword());
    
    try {
      Authentication authentication = authManager.authenticate(loginDetails);
      String token = tokenService.generateToken(authentication);
      return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
    
    
  }
}
