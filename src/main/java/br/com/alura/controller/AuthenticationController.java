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

import br.com.alura.controller.dto.LoginFormDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager authManager;
  
  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid LoginFormDto form) {
    UsernamePasswordAuthenticationToken loginDetails = new UsernamePasswordAuthenticationToken(form.getEmail(),
        form.getPassword());
    
    try {
      Authentication authentication = authManager.authenticate(loginDetails);
      return ResponseEntity.ok().build();
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
    
    
  }
}
