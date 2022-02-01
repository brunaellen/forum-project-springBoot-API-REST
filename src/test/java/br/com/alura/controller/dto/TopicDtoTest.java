package br.com.alura.controller.dto;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TopicDtoTest {
  
  @Test
  void simpleEqualsContract() {
    EqualsVerifier.simple().forClass(TopicDto.class).verify();
  }
}
