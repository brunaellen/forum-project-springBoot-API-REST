package br.com.alura.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TopicTest {
  
  @Test
  void simpleEqualsContract() {
    Answer firstAnswer = new Answer();
    firstAnswer.setMessage("first message");
    
    Answer secondAnswer = new Answer();
    secondAnswer.setMessage("second message");

    EqualsVerifier.simple().forClass(Topic.class).withPrefabValues(Answer.class, firstAnswer, secondAnswer).verify();
  }
}
