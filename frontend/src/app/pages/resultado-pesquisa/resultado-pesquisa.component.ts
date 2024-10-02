import { Component } from '@angular/core';
import { NavComponent } from "../../components/nav/nav.component";
import { CardFilmeComponent } from "../../components/card-filme/card-filme.component";
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-resultado-pesquisa',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent],
  templateUrl: './resultado-pesquisa.component.html',
  styleUrl: './resultado-pesquisa.component.scss'
})
export class ResultadoPesquisaComponent {
  filmes: FilmeDescoberta[] = [];
  titulo = new FormControl('');
  constructor(private filmeService: FilmeService, private route: ActivatedRoute) { }
  ngOnInit(): void {

    this.route.queryParams.subscribe(parametro => {
      var tituloParam = parametro['titulo'] || '';
      this.titulo.setValue(tituloParam);

    })
// estudar pipes
    this.titulo.valueChanges.subscribe(novoTitulo => {
      this.filmeService.pesquisarFilmeTitulo(novoTitulo || "").subscribe(filmes => {
        this.filmes = filmes
      });
    })

  }

}
