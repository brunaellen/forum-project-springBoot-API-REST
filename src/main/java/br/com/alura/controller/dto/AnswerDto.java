package br.com.alura.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.model.Answer;

public class AnswerDto {
  private Long id;
  private String message;
  private LocalDateTime dateOfCreation;
  private String author;
  
  public AnswerDto(Answer answer) {
    this.id = answer.getId();
    this.message = answer.getMessage();
    this.dateOfCreation = answer.getDateOfCreation();
    this.author = answer.getAuthor().getName();
  }

  public Long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getDateOfCreation() {
    return dateOfCreation;
  }

  public String getAuthor() {
    return author;
  } 
}
