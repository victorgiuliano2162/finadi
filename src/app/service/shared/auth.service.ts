import { UserService } from './user.service';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/app/models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private userService: UserService, private router: Router) {}

  // 1. Crie um BehaviorSubject para armazenar os dados do usuário.
  // Ele começa com 'null' porque o usuário não está logado no início.
  private currentUserSubject = new BehaviorSubject<User | null>(null);

  // 2. Exponha um Observable público. Os componentes vão "ouvir" este observable.
  // Eles nunca terão acesso direto ao Subject, o que é uma boa prática de encapsulamento.
  public currentUser$: Observable<User | null> =
    this.currentUserSubject.asObservable();

  // 3. Método para fazer o login (chamado pelo seu componente de login)
  public login(userData: User) {
    // Aqui você faria a chamada HTTP para seu backend...
    // Após a resposta bem-sucedida do backend:
    this.userService.getUserByEmail(userData).subscribe({
      next: (user) => {
        console.log('Login funcionou');
        console.log("Com id:"+ user.id);
        this.currentUserSubject.next(user);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        alert(err.error.message || 'Erro ao fazer login');
      },
    });

    // Atualize o BehaviorSubject com os dados do usuário.
    // Todos que estiverem "ouvindo" o currentUser$ serão notificados.
  }

  // 4. Método para fazer o logout
  public logout() {
    // Remova os dados do usuário, notificando todos os componentes.
    this.currentUserSubject.next(null);
    // Aqui você também redirecionaria para a página de login.
  }

  // 5. (Opcional) Um getter para obter o valor atual de forma síncrona, se necessário.
  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }
}
