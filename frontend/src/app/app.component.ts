import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { AutenticacaoService } from './services/autenticacao.service';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'Folope';

  constructor(private readonly autenticacaoServ:AutenticacaoService){
    this.autenticacaoServ.inicializarContextoAutenticacao()
  }

  
}
