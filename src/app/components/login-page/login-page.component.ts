import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Login } from 'src/app/models/login.model';
import {LoginService} from "../../service/login.service";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent {
  constructor(private loginService: LoginService) {}

  loginForm: Login = {
    email: "",
    password: "",
  };

  public submitLogin(loginForm: NgForm) {
    console.log("Submit chamado");
    console.log(loginForm.value);

    if(loginForm.valid) {
      console.log("Dados: "+ this.loginForm.email);
      console.log("Dados: "+ this.loginForm.password);

      this.loginService.testLogin(this.loginForm).subscribe({
        next: (res: Login) => {
          console.log("Enviou");
          console.log(res);
        },
        error: (err: HttpErrorResponse) => {
          console.log("Chamou o erro");
          console.log(err);
        }
      });
    } else {
      console.log("Formulário inválido");
    }
  }

}
  // public getLogin() {
  //   this.loginService.testGet().subscribe({
  //     next: (res: Login) => {
  //       console.log(res);
  //     }
  //   })
  // }
