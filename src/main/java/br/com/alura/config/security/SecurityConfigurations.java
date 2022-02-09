package br.com.alura.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
  
  @Autowired
  private AuthenticationService authenticationService;
  
  //method for configuration of authentication
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
  }
  
  //method for configuration of authorization
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
      .antMatchers(HttpMethod.GET, "/topics/listAll").permitAll()
      .anyRequest().authenticated()
      .and().formLogin();
  }
}
