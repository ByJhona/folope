import { Component } from '@angular/core';
import { Filme } from '../../types/Filme';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { FilmeService } from '../../services/filme.service';
import { CardGFilmeComponent } from "../../components/card-g-filme/card-g-filme.component";
import { NavComponent } from '../../components/nav/nav.component';

@Component({
    selector: 'app-filme',
    imports: [CardGFilmeComponent, NavComponent],
    templateUrl: './filme.component.html',
    styleUrl: './filme.component.scss'
})
export class FilmeComponent {
  filme!: Filme
  dataLancamento = ""

  constructor(private readonly filmeService: FilmeService, private readonly route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.queryParams.pipe(
      switchMap(parametro => {
        let id = parametro['id'] || 0;
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
