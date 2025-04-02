import { HttpEvent, HttpHandlerFn, HttpRequest, HttpResponse } from '@angular/common/http';
import { catchError, Observable, of, throwError } from 'rxjs';

export function NaoAutorizadoInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> {
  return next(req).pipe(
    catchError((erro) => {
      if (erro.status === 403) {
        console.warn('Ação específica para 403 - Não autorizado');
        
        // Retorna um HttpResponse válido para evitar erro de tipagem
        return of(new HttpResponse({ body: { mensagem: 'Acesso negado' }, status: 403 }));
      }

      console.error('Erro detectado:', erro); // Agora só loga se NÃO for 403
      return throwError(() => erro);
    })
  );
}
