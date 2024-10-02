import { Component } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CardFilmeComponent } from '../../components/card-filme/card-filme.component';
import { FilmeService } from '../../services/filme.service';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { NgFor } from '@angular/common';
import { FilmeDestaqueComponent } from "../../components/filme-destaque/filme-destaque.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent, NgFor, FilmeDestaqueComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  filmesSemanais: FilmeDescoberta[] = []
  filmeDestaque!: FilmeDescoberta
  constructor(private filmeService: FilmeService) { }
  ngOnInit(): void {
    this.filmeService.listarFilmes().subscribe((data) => {
      this.filmesSemanais = data
      this.filmeDestaque = data[4]
      console.log(this.filmeDestaque)
    });
  }
}
