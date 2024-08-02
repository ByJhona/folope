import { Component, Input } from '@angular/core';
import { FilmeDescoberta } from '../types/FilmeDescoberta';

@Component({
  selector: 'app-filme-destaque',
  standalone: true,
  imports: [],
  templateUrl: './filme-destaque.component.html',
  styleUrl: './filme-destaque.component.scss'
})
export class FilmeDestaqueComponent {
  @Input() filme!: FilmeDescoberta
}
