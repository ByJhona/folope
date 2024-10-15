import { Component } from '@angular/core';
import { Filme } from '../../types/Filme';
import { Router } from 'express';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { FilmeService } from '../../services/filme.service';
import { CardGFilmeComponent } from "../../components/card-g-filme/card-g-filme.component";
import { FilmeInformacaoComponent } from "../../components/filme-informacao/filme-informacao.component";

@Component({
  selector: 'app-filme',
  standalone: true,
  imports: [CardGFilmeComponent, FilmeInformacaoComponent],
  templateUrl: './filme.component.html',
  styleUrl: './filme.component.scss'
})
export class FilmeComponent {
  filme!: Filme
  dataLancamento = ""

  constructor(private filmeService: FilmeService, private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.queryParams.pipe(
      switchMap(parametro => {
        var id = parametro['id'] || 0;
        return this.filmeService.pesquisarFilmeId(id);
      })
    ).subscribe(filme => {
      this.filme = filme;
      this.dataLancamento = this.formatarData(filme.dataLancamento);
        console.log(filme)
    })
  }

  formatarData(data: Date) {
    return new Date(data).toLocaleDateString('pt-BR', {
      day: 'numeric',
      month: 'short',  // Exibe o mês abreviado
      year: 'numeric'
    });
  }

}
