package br.com.alura.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("development")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter{

  //method for configuration of authorization
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
      .antMatchers("/**").permitAll()
      .and().csrf().disable();
  }
}
