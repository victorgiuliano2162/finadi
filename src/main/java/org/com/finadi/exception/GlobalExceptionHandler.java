package org.com.finadi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//  @ExceptionHandler(UserNotFoundException.class)
//  public ResponseEntity<String> handleNotFoundException(UserNotFoundException ex) {
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
    Map<String, String> erro = new HashMap<>();
    erro.put("erro", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
    Map<String, String> erro = new HashMap<>();
    erro.put("erro", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
  }

  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handleException(ParameterinvalidException ex) {
    Map<String, String> erro = new HashMap<>();
    erro.put("erro", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
  }

}
