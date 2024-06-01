import { Component } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CardFilmeComponent } from '../../components/card-filme/card-filme.component';
import { FilmeService } from '../../services/filme.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavComponent, CardFilmeComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private filmeService: FilmeService) { }
  ngOnInit():void {
    this.filmeService.listarFilmes().subscribe((data) => {
      console.log(data)
    });
  }
}
