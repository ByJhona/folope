import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { Token } from '../types/Token';

@Injectable({
  providedIn: 'root',
})
export class AutenticacaoService {
  private readonly apiUrl: string = environment.apiUrl;
  constructor(private readonly httpClient: HttpClient) {}

  login(nome :string, senha:string):Observable<Token>{
    return this.httpClient.post<Token>(this.apiUrl + "/usuario/login", {"nome": nome, "senha":senha});
  }
}
