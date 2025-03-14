import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../types/JwtPayload';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private token: string | null = null;

  obterToken(): string | null {
    return this.token ?? localStorage.getItem('token');
  }

  definirToken(token: string): void {
    this.token = token;
    localStorage.setItem('token', token);
  }

  apagarToken(): void {
    this.token = null;
  }

  validarToken(): boolean {
    return !!this.token;
  }

  obterNomeUsuario() {
    const token = this.token ?? localStorage.getItem('token') ?? "Thias";
    
      const jwt = jwtDecode<JwtPayload>(token);
      console.log(jwt)
      return jwt.name;
  }
}
