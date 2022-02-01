package br.com.alura.controller.dto;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TopicFormDtoTest {
  
  @Test
  void simpleEqualsContract() {
    EqualsVerifier.simple().forClass(TopicFormDto.class).verify();
  }
}
