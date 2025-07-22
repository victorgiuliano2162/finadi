package org.com.finadi.exception;

public class ParameterinvalidException extends RuntimeException {
  public ParameterinvalidException(String parameter) {
    super(parameter);
  }
}
