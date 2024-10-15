import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-botao',
  standalone: true,
  imports: [MatIcon],
  templateUrl: './botao.component.html',
  styleUrl: './botao.component.scss'
})
export class BotaoComponent {
  @Input() label:string = '';
  @Input() icone:string = '';
  @Input() estilo:string = "primario"

  @Output() abrir: EventEmitter<boolean> = new EventEmitter<boolean>();
  acionarClick():void {
    this.abrir.emit(true);
  }
}
