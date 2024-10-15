import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatIcon } from '@angular/material/icon';


@Component({
  selector: 'app-paginator',
  standalone: true,
  imports: [MatIcon],
  templateUrl: './paginator.component.html',
  styleUrl: './paginator.component.scss'
})
export class PaginatorComponent {
  @Input() maxPaginas:number = 1;
  @Input() paginaAtual:number = 1;

  @Output() alterarPagina: EventEmitter<number> = new EventEmitter<number>();

  avancarPagina() {
    if (this.paginaAtual < this.maxPaginas) {
      this.alterarPagina.emit(this.paginaAtual += 1);
    }
  }

  voltarPagina() {
    if (this.paginaAtual > 1) {
      this.alterarPagina.emit(this.paginaAtual -= 1);
    }
  }

}
