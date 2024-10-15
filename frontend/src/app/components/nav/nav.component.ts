import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { BarraPesquisarComponent } from "../barra-pesquisar/barra-pesquisar.component";
import { BotaoComponent } from "../botao/botao.component";
import { RouterLink } from '@angular/router';



@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatIconModule, BarraPesquisarComponent, BotaoComponent, RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss'
})
export class NavComponent {
  pesquisar =false



  trocar(){
    this.pesquisar = !this.pesquisar
  }
  

}
