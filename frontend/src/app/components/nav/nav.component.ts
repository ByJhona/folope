import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { BarraPesquisarComponent } from '../barra-pesquisar/barra-pesquisar.component';
import { BotaoComponent } from '../botao/botao.component';
import { Router, RouterLink } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-nav',
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    BarraPesquisarComponent,
    BotaoComponent,
    RouterLink,
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss',
})
export class NavComponent {
  pesquisar = false;
  nomeUsuario: string | null = null;

  constructor(
    private readonly router: Router,
    public readonly tokenServ: TokenService
  ) {}
  
  ngOnInit(): void {
    if (this.tokenServ.validarToken()) {
      this.nomeUsuario = this.tokenServ.obterNomeUsuario();
    }
  }

  trocar() {
    this.pesquisar = !this.pesquisar;
  }

  login(click: boolean): void {
    if (click) {
      this.router.navigate(['/login']);
    }
  }
}
