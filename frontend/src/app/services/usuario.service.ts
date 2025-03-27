import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Usuario } from '../types/Usuario';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { AutenticacaoService } from './autenticacao.service';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  usuario = new BehaviorSubject<Usuario | null>(null);
  usuario$ = this.usuario.asObservable();
  ehAutenticado = false;
  private readonly apiUrl: string = environment.apiUrl;

  constructor(
    public readonly client: HttpClient,
    private readonly autenticacaoServ: AutenticacaoService
  ) {
    this.autenticacaoServ.ehAutenticado$.subscribe((estado:boolean) => {
      this.ehAutenticado = estado;
    });
  }

  obterUsuario(): void {
    if (this.ehAutenticado) {
      this.client
        .get<Usuario>(this.apiUrl + '/usuario/obter-usuario', {
          withCredentials: true,
        })
        .subscribe((usuario) => {
          console.log(usuario);
          this.usuario.next(usuario);
        });
    }
  }
}
