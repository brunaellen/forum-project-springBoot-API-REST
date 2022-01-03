package br.com.alura.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.model.Topic;

public class TopicDto {
  private Long id;
  private String title;
  private String message;
  private LocalDateTime dateOfCriation;
  
  public TopicDto(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.dateOfCriation = topic.getDateOfCriation();
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

  public static List<TopicDto> convertATopicListToTopicDtoList(List<Topic> topicAsList) {
    return topicAsList.stream().map(TopicDto::new).collect(Collectors.toList());
  }
}
