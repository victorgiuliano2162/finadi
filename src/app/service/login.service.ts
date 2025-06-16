import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Login } from "../models/login.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpCliente: HttpClient) { }

  api: string = "http://localhost:8080";

  public testLogin(login: Login): Observable<Login> {
    return this.httpCliente.post<Login>(`${this.api}/login`, login);
  }

  public testGet(): Observable<Login> {
    return this.httpCliente.get<Login>(`${this.api}/login`);
  }
}
