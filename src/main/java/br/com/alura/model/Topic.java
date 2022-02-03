package br.com.alura.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String message;
  private LocalDateTime dateOfCriation;
  
  @Enumerated(EnumType.STRING)
  private StatusTopic status = StatusTopic.NOT_ANSWERED;
  
  @ManyToOne
  private User author;
  
  @ManyToOne
  private Course course;
  
  @OneToMany(mappedBy = "topic")
  private List<Answer> answers = new ArrayList<>();

  public Topic() {
  }

  public Topic(String title, String message, Course course) {
    this.title = title;
    this.message = message;
    this.course = course;
  }

  public Topic(String title, String message, User author, Course course) {
    this.title = title;
    this.message = message;
    this.author = author;
    this.course = course;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getDateOfCriation() {
    return dateOfCriation;
  }

  public void setDateOfCriation() {
    this.dateOfCriation = LocalDateTime.now();
  }

  public StatusTopic getStatus() {
    return status;
  }

  public void setStatus(StatusTopic status) {
    this.status = status;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((answers == null) ? 0 : answers.hashCode());
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((course == null) ? 0 : course.hashCode());
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
    Topic other = (Topic) obj;
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
    if (course == null) {
      if (other.course != null)
        return false;
    } else if (!course.equals(other.course))
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
