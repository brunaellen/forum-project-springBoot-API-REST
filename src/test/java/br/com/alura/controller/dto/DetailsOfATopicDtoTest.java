package br.com.alura.controller.dto;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class DetailsOfATopicDtoTest {
  
  @Test
  void simpleEqualsContract() {
    EqualsVerifier.simple().forClass(DetailsOfATopicDto.class).verify();
  }
}
