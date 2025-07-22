import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private httpCliente: HttpClient) { }

  api: string = "http://localhost:8080/api/user";

  public saveCategoria(categoria: any, userID: string) {
    return this.httpCliente.post(`${this.api}/${userID}`, categoria);
  }
}
