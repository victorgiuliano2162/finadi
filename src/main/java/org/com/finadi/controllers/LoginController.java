package org.com.finadi.controllers;

import org.com.finadi.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @PostMapping("/login")
  public void login(@RequestBody Login user) {
    System.out.println(user.getEmail());
    System.out.println(user.getPassword());
  }

//  @GetMapping("/login")
//  public Login testGet() {
//    System.out.println("Get chamado");
//    return new Login("user@test.com", "te amo, pitoco");
//  }
}
