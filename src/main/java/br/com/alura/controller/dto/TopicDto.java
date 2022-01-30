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

  public TopicDto() {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dateOfCriation == null) ? 0 : dateOfCriation.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
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
    TopicDto other = (TopicDto) obj;
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
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

  
}
