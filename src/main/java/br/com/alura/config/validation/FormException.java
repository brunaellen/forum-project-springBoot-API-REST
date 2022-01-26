package br.com.alura.config.validation;

public class FormException {
  private String field;
  private String error;
  
  public FormException(String field, String error) {
    this.field = field;
    this.error = error;
  }

  public String getField() {
    return field;
  }

  public String getError() {
    return error;
  }
}
