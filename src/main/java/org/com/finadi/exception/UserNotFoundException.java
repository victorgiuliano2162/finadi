package org.com.finadi.exception;

public class UserNotFoundException extends Exception{
  public UserNotFoundException(String mensagem) {
    super(mensagem);
  }
}
