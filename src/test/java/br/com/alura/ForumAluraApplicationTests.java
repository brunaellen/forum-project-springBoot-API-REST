package br.com.alura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alura.config.security.service.AuthenticationService;
import br.com.alura.config.security.service.TokenService;
import br.com.alura.repository.UserRepository;

@SpringBootTest
class ForumAluraApplicationTests {
  
  @MockBean
  private AuthenticationService authenticationService;
  
  @MockBean
  private TokenService tokenService;
  
  @MockBean
  private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

}
