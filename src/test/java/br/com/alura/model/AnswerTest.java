package br.com.alura.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class AnswerTest {
  
  @Test
  void simpleEqualsContract() {
    Topic firstTopic = new Topic("title", "first message", new Course("first name", "first category"));
    Topic secondTopic = new Topic("second title ", "second message", new Course("second name", "second category"));

    EqualsVerifier.simple().forClass(Answer.class).withPrefabValues(Topic.class, firstTopic, secondTopic).verify();
  }
}
