package org.com.finadi.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController implements ErrorController {

  @RequestMapping("/error")
  public String handleError() {
    // Redireciona para o index.html (Angular cuidará do roteamento)
    System.out.println("Chamou o handleError");
    return "forward:/index.html";
  }
}
