import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { Conta } from 'src/app/models/conta.model';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(private httpClient: HttpClient) { }

  api: string = "http://localhost:8080/api/user";

  //criarNovaConta(String userID, Conta conta)
  public criarNovaContaWithPath(userID: string, conta: Conta) {
    //${userID}/
    return this.httpClient.post(`/account`, conta);
  }

  public createNewAccount(conta: Conta) {
    return this.httpClient.post<Conta>(`${this.api}/account`, conta);
 }

  public getContasByUserId(userId: string) {
    return this.httpClient.get<Conta[]>(`${this.api}/${userId}/accounts`);
  }

  public getContaById(id: string) {
    return this.httpClient.get<Conta>(`${this.api}/account/${id}`);
  }

}
