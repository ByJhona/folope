import {
  HttpEvent,
  HttpEventType,
  HttpHandlerFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';

export function logingInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> {
  let usuarioServ = inject(UsuarioService);
  return next(req).pipe(
    tap((event) => {
      if (event.type === HttpEventType.Response) {
      }
    })
  );
}
