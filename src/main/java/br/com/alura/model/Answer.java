package br.com.alura.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String message;
  
  @ManyToOne
  private Topic topic;
  private LocalDateTime dateOfCreation;
  
  @ManyToOne
  private User author;
  private Boolean solution = false;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public LocalDateTime getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(LocalDateTime dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public Boolean getSolution() {
    return solution;
  }

  public void setSolution(Boolean solution) {
    this.solution = solution;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + ((solution == null) ? 0 : solution.hashCode());
    result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
    Answer other = (Answer) obj;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (dateOfCreation == null) {
      if (other.dateOfCreation != null)
        return false;
    } else if (!dateOfCreation.equals(other.dateOfCreation))
      return false;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    if (solution == null) {
      if (other.solution != null)
        return false;
    } else if (!solution.equals(other.solution))
      return false;
    if (topic == null) {
      if (other.topic != null)
        return false;
    } else if (!topic.equals(other.topic))
      return false;
    return true;
  }
}
