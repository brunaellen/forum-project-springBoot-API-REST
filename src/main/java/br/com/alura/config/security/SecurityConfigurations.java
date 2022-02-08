package br.com.alura.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
  
  //method for configuration of authorization
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/topics/listAll").permitAll();
  }

}
