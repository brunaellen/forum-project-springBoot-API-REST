package br.com.alura.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TopicFormDto {
  
  @NotNull @NotEmpty @Length(min = 5)
  private String title;
  @NotNull @NotEmpty @Length(min = 10)
  private String message;
  @NotNull @NotEmpty
  private String courseName;

  public TopicFormDto(String title, String message, String courseName) {
    this.title = title;
    this.message = message;
    this.courseName = courseName;
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
  
  public String getCourseName() {
    return courseName;
  }
  
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
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
    TopicFormDto other = (TopicFormDto) obj;
    if (courseName == null) {
      if (other.courseName != null)
        return false;
    } else if (!courseName.equals(other.courseName))
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
