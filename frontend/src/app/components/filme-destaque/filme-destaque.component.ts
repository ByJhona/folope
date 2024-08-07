import { Component, Input } from '@angular/core';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { BntTrailerComponent } from "../bnt-trailer/bnt-trailer.component";

@Component({
  selector: 'app-filme-destaque',
  standalone: true,
  imports: [BntTrailerComponent],
  templateUrl: './filme-destaque.component.html',
  styleUrl: './filme-destaque.component.scss'
})
export class FilmeDestaqueComponent {
  @Input() filme!: FilmeDescoberta
}
