import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CardFilmeComponent } from '../../components/card-filme/card-filme.component';
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { FilmeDestaqueComponent } from '../../components/filme-destaque/filme-destaque.component';
import { Filme } from '../../types/Filme';
import { switchMap } from 'rxjs';
import { UsuarioService } from '../../services/usuario.service';
import { OAuthService } from 'angular-oauth2-oidc';
import { authCodeFlowConfig } from '../../config/auth-config';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent, FilmeDestaqueComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  filmesSemanais: FilmeDescoberta[] = [];
  filmeDestaque!: Filme;
  constructor(
    private readonly filmeServ: FilmeService,
    private readonly usuarioServ: UsuarioService,
    private readonly oauthServ: OAuthService,
    @Inject(PLATFORM_ID) private readonly platformId: Object
  ) {}
  ngOnInit(): void {
    this.filmeServ
      .listarFilmes()
      .pipe(
        switchMap((filmes) => {
          this.filmesSemanais = filmes;
          const idFilme = filmes[0]?.id;
          console.log(idFilme);
          return this.filmeServ.pesquisarFilmeId(idFilme);
        })
      )
      .subscribe((filme) => {
        this.filmeDestaque = filme;
      });

  }
}
