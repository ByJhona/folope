import { Component, Input } from '@angular/core';
import { BotaoComponent } from "../botao/botao.component";

@Component({
  selector: 'app-card-g-filme',
  standalone: true,
  imports: [BotaoComponent],
  templateUrl: './card-g-filme.component.html',
  styleUrl: './card-g-filme.component.scss'
})
export class CardGFilmeComponent {
  @Input() urlPoster!: string

}
