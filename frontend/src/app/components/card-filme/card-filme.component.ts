import { Component, Input } from '@angular/core';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { ProgressSpinnerMode, MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSliderModule } from '@angular/material/slider';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-card-filme',
  standalone: true,
  imports: [MatCardModule, MatRadioModule, FormsModule, MatSliderModule, MatProgressSpinnerModule],
  templateUrl: './card-filme.component.html',
  styleUrl: './card-filme.component.scss'
})
export class CardFilmeComponent {
  @Input() filme!: FilmeDescoberta;
  constructor(private router: Router) { }

  paginaFilme() {
    this.router.navigate(['/filme'], { queryParams: { id: this.filme.id , titulo: this.filme.titulo} });
  }

}
