package br.com.alura.config.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  void authenticate_givenAloginForm_shouldReturnBadRequestIfLoginIsWrong() throws Exception {
    final String loginFormDto = "{\n"
        + "\"email\": \"invalid@email.com\",\n"
        + "\"password\": \"98182671537\"\n"
        + "}";

    mockMvc
      .perform(post("/auth")
          .content(loginFormDto)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

}
