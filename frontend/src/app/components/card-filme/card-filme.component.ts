import { Component, Input } from '@angular/core';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';

@Component({
  selector: 'app-card-filme',
  standalone: true,
  imports: [],
  templateUrl: './card-filme.component.html',
  styleUrl: './card-filme.component.scss'
})
export class CardFilmeComponent {
@Input() filme!: FilmeDescoberta;
}
