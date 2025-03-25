import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../types/JwtPayload';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private token: string | null = null;

  obterToken(): string {
    return this.token ?? sessionStorage.getItem('token') ?? "";
  }

  definirToken(token: string): void {
    this.token = token;
    sessionStorage.setItem('token', token);
  }

  apagarToken(): void {
    this.token = null;
  }

  validarToken(): boolean {
    return !!this.token;
  }

  obterNomeUsuario() {
    const token = this.token ?? sessionStorage.getItem('token') ?? 'Thias';

    const jwt = jwtDecode<JwtPayload>(token);
    console.log(jwt);
    return jwt.sub;
  }
}
