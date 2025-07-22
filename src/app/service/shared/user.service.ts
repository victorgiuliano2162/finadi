import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpCliente: HttpClient) {}

  api: string = 'http://localhost:8080/api/user';

  public registerUser(user: User): Observable<User> {
    return this.httpCliente.post<User>(`${this.api}`, user);
  }

  public getUserByCpf(cpf: string): Observable<User> {
    return this.httpCliente.get<User>(`${this.api}/cpf/${cpf}`);
  }

  public getUserByEmail(user: User): Observable<User> {
    return this.httpCliente.post<User>(`${this.api}/login`, user);
  }

  public getByCpf(cpf: string): Observable<User> {
    const cpfLimpo = cpf.replace(/\D/g, '');

    // Cria os parâmetros de forma segura
    const params = new HttpParams().set('cpf', cpfLimpo);

    // Passa os parâmetros como uma opção para a requisição GET
    return this.httpCliente.get<User>(this.api, { params: params });
  }
}
