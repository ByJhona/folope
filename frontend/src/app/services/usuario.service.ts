import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Usuario } from '../types/Usuario';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  usuario = new BehaviorSubject<Usuario | null>(null);
  usuario$ = this.usuario.asObservable();
  private readonly apiUrl: string = environment.apiUrl;

  constructor(public readonly client: HttpClient) {}

  obterUsuario(): Observable<Usuario> {
    return this.client.get<Usuario>(this.apiUrl + '/obter-usuario');
  }
}
