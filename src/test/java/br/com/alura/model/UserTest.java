package br.com.alura.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class UserTest {
  @Test
  void simpleEqualsContract() {
    EqualsVerifier.simple().forClass(User.class).verify();
  }
}
