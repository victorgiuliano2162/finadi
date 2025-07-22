import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/shared/auth.service';
import { User } from 'src/app/models/usuario.model';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  constructor(private authService: AuthService, private router: Router) {}

  loginForm: User = {
    cpf: '',
    name: '',
    email: '',
    password: '',
  };

  public submitLogin(loginForm: NgForm) {
    console.log('Submit chamado');
    console.log(loginForm.value);

    if (loginForm.valid) {
      console.log('Dados: ' + this.loginForm.email);
      console.log('Dados: ' + this.loginForm.password);

      this.authService.login(this.loginForm);
    } else {
      console.log('Formulário inválido');
      alert('Formulário inválido');
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
