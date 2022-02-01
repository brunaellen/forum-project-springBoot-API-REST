package br.com.alura.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class CourseTest {
  
  @Test
  void simpleEqualsContract() {
    EqualsVerifier.simple().forClass(Course.class).verify();
  }
}
