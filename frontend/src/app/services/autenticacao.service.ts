import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject } from 'rxjs';
import { OAuthService } from 'angular-oauth2-oidc';
import { isPlatformBrowser } from '@angular/common';
import { authCodeFlowConfig } from '../config/auth-config';

@Injectable({
  providedIn: 'root',
})
export class AutenticacaoService {
  private readonly apiUrl: string = environment.apiUrl;
  ehAutenticado = new BehaviorSubject(false);
  ehAutenticado$ = this.ehAutenticado.asObservable();
  constructor(
    private readonly oAuthServ: OAuthService,
    @Inject(PLATFORM_ID) private readonly platformId: Object
  ) {
    if (isPlatformBrowser(this.platformId)) {
      this.oAuthServ.configure(authCodeFlowConfig);
    }
  }

  inicializarContextoAutenticacao(){
    if (isPlatformBrowser(this.platformId)) {
      this.oAuthServ.loadDiscoveryDocument().then(() => this.oAuthServ.tryLogin())
      
    }
  }



  login(): void {
    if (!this.oAuthServ.hasValidAccessToken()) {
      this.oAuthServ.loadDiscoveryDocument().then(()=> this.oAuthServ.initLoginFlow())
    }
  }
}
