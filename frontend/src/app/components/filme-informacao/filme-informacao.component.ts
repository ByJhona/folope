import { Component, Input } from '@angular/core';
import { Filme } from '../../types/Filme';
import { CardGFilmeComponent } from '../card-g-filme/card-g-filme.component';

@Component({
  selector: 'app-filme-informacao',
  standalone: true,
  imports: [CardGFilmeComponent],
  templateUrl: './filme-informacao.component.html',
  styleUrl: './filme-informacao.component.scss'
})
export class FilmeInformacaoComponent {
  @Input() filme!: Filme;

}
