package org.com.finadi.exception;

public class ParameterinvalidException extends Exception {
  public ParameterinvalidException(String parameter) {
    super(parameter+ " invalid");
  }
}
