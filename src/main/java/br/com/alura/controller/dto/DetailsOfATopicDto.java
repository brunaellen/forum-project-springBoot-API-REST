package br.com.alura.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.model.StatusTopic;
import br.com.alura.model.Topic;

public class DetailsOfATopicDto {
  private Long id;
  private String title;
  private String message;
  private LocalDateTime dateOfCriation;
  private String author;
  private StatusTopic status;
  private List<AnswerDto> answers;
  
  public DetailsOfATopicDto(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.dateOfCriation = topic.getDateOfCriation();
    this.author = topic.getAuthor().getName();
    this.status = topic.getStatus();
    this.answers = new ArrayList<>();
  }
  
  public void setAnswers(Topic topic) {
    List<AnswerDto> answerList = topic.getAnswers()
        .stream()
        .map(AnswerDto::new)
        .collect(Collectors.toList());
    
    this.answers.addAll(answerList);
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getDateOfCriation() {
    return dateOfCriation;
  }

  public String getAuthor() {
    return author;
  }

  public StatusTopic getStatus() {
    return status;
  }

  public List<AnswerDto> getAnswers() {
    return answers;
  }
}
