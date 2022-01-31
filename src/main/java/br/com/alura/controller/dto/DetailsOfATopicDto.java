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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((answers == null) ? 0 : answers.hashCode());
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((dateOfCriation == null) ? 0 : dateOfCriation.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DetailsOfATopicDto other = (DetailsOfATopicDto) obj;
    if (answers == null) {
      if (other.answers != null)
        return false;
    } else if (!answers.equals(other.answers))
      return false;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (dateOfCriation == null) {
      if (other.dateOfCriation != null)
        return false;
    } else if (!dateOfCriation.equals(other.dateOfCriation))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    if (status != other.status)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }
}
