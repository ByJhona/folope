import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../types/JwtPayload';
import { SsrCookieService } from 'ngx-cookie-service-ssr';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private token: string | null = null;

  constructor(private readonly cookieService: SsrCookieService) {}

  obterToken(): string {
    return this.cookieService.get('token');
  }

  verificarToken(): boolean {
    return this.cookieService.check('token');
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
