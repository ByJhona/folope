import { Component } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CardFilmeComponent } from '../../components/card-filme/card-filme.component';
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { NgFor } from '@angular/common';
import { FilmeDestaqueComponent } from "../../components/filme-destaque/filme-destaque.component";
import { Filme } from '../../types/Filme';
import { concatMap, switchMap } from 'rxjs';
import { PaginatorComponent } from "../../components/paginator/paginator.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent, NgFor, FilmeDestaqueComponent, PaginatorComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  filmesSemanais: FilmeDescoberta[] = []
  filmeDestaque!: Filme
  constructor(private filmeService: FilmeService) { }
  ngOnInit(): void {
    this.filmeService.listarFilmes().pipe(
     switchMap(filmes => {
      this.filmesSemanais = filmes
      const idFilme = filmes[0]?.id
      console.log(idFilme)
      return this.filmeService.pesquisarFilmeId(idFilme)
     })
    )
    .subscribe((filme) => {
      this.filmeDestaque = filme
    });
  }
}
