import { Component } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CardFilmeComponent } from '../../components/card-filme/card-filme.component';
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { FilmeDestaqueComponent } from '../../components/filme-destaque/filme-destaque.component';
import { Filme } from '../../types/Filme';
import { switchMap } from 'rxjs';

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
  constructor(private readonly filmeService: FilmeService) {}
  ngOnInit(): void {
    this.filmeService
      .listarFilmes()
      .pipe(
        switchMap((filmes) => {
          this.filmesSemanais = filmes;
          const idFilme = filmes[6]?.id;
          console.log(idFilme);
          return this.filmeService.pesquisarFilmeId(idFilme);
        })
      )
      .subscribe((filme) => {
        this.filmeDestaque = filme;
      });
  }
}
