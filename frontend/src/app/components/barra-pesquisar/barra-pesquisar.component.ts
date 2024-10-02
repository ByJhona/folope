import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { query } from 'express';

@Component({
  selector: 'app-barra-pesquisar',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './barra-pesquisar.component.html',
  styleUrl: './barra-pesquisar.component.scss'
})
export class BarraPesquisarComponent {
  pesquisaForm = new FormControl('')

  constructor(private router: Router) { }

  pesquisar() {
    this.router.navigate(['/pesquisar'], {queryParams: {titulo:this.pesquisaForm.value}});
  }
}
