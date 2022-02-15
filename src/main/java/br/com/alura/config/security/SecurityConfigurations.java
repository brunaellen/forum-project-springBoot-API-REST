package br.com.alura.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.config.security.service.AuthenticationService;
import br.com.alura.config.security.service.TokenService;
import br.com.alura.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
  
  @Autowired
  private AuthenticationService authenticationService;
  
  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;
  
  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
  
  //method for configuration of authentication
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
  }
  
  //method for configuration of authorization
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
      .antMatchers(HttpMethod.GET, "/topics/listAll").authenticated()
      .antMatchers(HttpMethod.POST, "/auth").permitAll()
      .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
      .antMatchers(HttpMethod.DELETE, "/topics/delete/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and().csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().addFilterBefore(new AuthenticationByTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
    .antMatchers("/h2-console/**", "/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
  }
}
