import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { BarraPesquisarComponent } from '../barra-pesquisar/barra-pesquisar.component';
import { BotaoComponent } from '../botao/botao.component';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../types/Usuario';
import { AutenticacaoService } from '../../services/autenticacao.service';

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
  usuario: Usuario | null = null;
  ehAutenticado = false;

  constructor(
    private readonly router: Router,
    private readonly usuarioServ: UsuarioService,
    private readonly autenticacaoServ: AutenticacaoService
  ) {
    this.usuarioServ.usuario$.subscribe((usuario) => {
      this.usuario = usuario;
    });
    this.autenticacaoServ.ehAutenticado$.subscribe((estado:boolean) => {
      this.ehAutenticado = estado;
    });
  }

  ngOnInit(){
    this.usuarioServ.obterUsuario()
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
