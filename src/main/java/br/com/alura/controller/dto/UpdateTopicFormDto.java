package br.com.alura.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UpdateTopicFormDto {
  
  @NotNull @NotEmpty @Length(min = 5)
  private String title;
  @NotNull @NotEmpty @Length(min = 10)
  private String message;
  
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
}
