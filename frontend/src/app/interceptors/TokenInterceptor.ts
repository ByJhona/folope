import { HttpEvent, HttpHandlerFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';

export function TokenInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> {
 // const token = inject(TokenService).obterToken();

  const reqToken = req.clone({
    headers: req.headers.append('Bearer', "token"),
  });

  return next(reqToken);
}
