import { Component } from '@angular/core';
import { LoginService } from '../../formularios/login.service';
import { ReactiveFormsModule } from '@angular/forms';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { NavComponent } from '../../components/nav/nav.component';
import { Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NavComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  constructor(
    public readonly loginServ: LoginService,
    public autenticacaoServ: AutenticacaoService,
    private readonly usuarioServ: UsuarioService,
    private readonly router: Router
  ) {}

  submeterLogin() {
    const nome = this.loginServ.obterNome();
    const senha = this.loginServ.obterSenha();
    this.autenticacaoServ.login(nome, senha).subscribe(() => {
      this.router.navigate(['/home']);
      this.autenticacaoServ.verificarAutenticado();
      this.usuarioServ.obterUsuario();
    });
  }
}
