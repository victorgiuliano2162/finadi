import { Router } from '@angular/router';
import { UserService } from '../../service/shared/user.service';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/usuario.model';
import { delay } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(private userService: UserService, private router: Router) {}

  subscribeUserFrom: User = {
    cpf: '',
    name: '',
    email: '',
    password: '',
  };

  public submitUserForm(subscribeUserFrom: NgForm) {
    console.log('Submit chamado');

    if (subscribeUserFrom.valid) {
      console.log('Dados: ' + this.subscribeUserFrom.email);

      this.userService.registerUser(this.subscribeUserFrom).subscribe({
        next: (res: User) => {
          console.log('Usuário registrado com sucesso');
          console.log(res);
          setTimeout(() => {}, 2000);
          this.router.navigate(['/dashboard']);
        },
        error: (err: any) => {
          alert(err.error.erro);
          console.log();
        },
      });
    } else {
      console.log('Formulário inválido');
      alert('Impossível realizar operação');
    }
  }

  // Função para ser chamada na submissão do formulário
}
