import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject, catchError, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AutenticacaoService {
  private readonly apiUrl: string = environment.apiUrl;
  ehAutenticado = new BehaviorSubject(false);
  ehAutenticado$ = this.ehAutenticado.asObservable();
  constructor(private readonly httpClient: HttpClient) {
  }

  login(identificador: string, senha: string): Observable<void> {
    return this.httpClient.post<void>(
      this.apiUrl + '/login',
      { identificador: identificador, senha: senha },
      { withCredentials: true }
    );
  }

  verificarAutenticado(): void {
    this.httpClient
      .get<boolean>(this.apiUrl + '/usuario-autenticado', {
        withCredentials: true,
      })
      .pipe(
        catchError(() => {
          this.ehAutenticado.next(false);
          return of(false); // Evita que o Angular trate como erro
        })
      )
      .subscribe({
        next: () => this.ehAutenticado.next(true),
      });
  }
  
}
