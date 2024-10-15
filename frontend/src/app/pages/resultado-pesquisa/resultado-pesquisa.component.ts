import { Component } from '@angular/core';
import { NavComponent } from "../../components/nav/nav.component";
import { CardFilmeComponent } from "../../components/card-filme/card-filme.component";
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { PaginatorComponent } from "../../components/paginator/paginator.component";
import { FilmesResponse } from '../../types/FilmesResponse';

@Component({
  selector: 'app-resultado-pesquisa',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent, PaginatorComponent],
  templateUrl: './resultado-pesquisa.component.html',
  styleUrl: './resultado-pesquisa.component.scss'
})
export class ResultadoPesquisaComponent {
  filmesResponse: FilmesResponse = new FilmesResponse();
  titulo = new FormControl('');
  paginaAtual: number = 1;
  constructor(private filmeService: FilmeService, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(parametro => {
      var tituloParam = parametro['titulo'] || '';
      this.titulo.setValue(tituloParam);
      this.pesquisarFilme(1);

    })

    this.titulo.valueChanges.subscribe(novoTitulo => {
      this.pesquisarFilme(1);
      this.paginaAtual = 1;
    })

  }


  pesquisarFilme(numPagina: number): void {
    this.filmeService.pesquisarFilmeTitulo(this.titulo.value || "", numPagina).subscribe(filmesResponse => {
      this.filmesResponse = filmesResponse
    });
  }

  alterarPagina(numPagina: number) {
    
    if (numPagina <= this.filmesResponse.quantPaginas) {
       
      this.paginaAtual = numPagina;
      this.pesquisarFilme(numPagina);
      

    }
  }

}
