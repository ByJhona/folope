import { Component, inject, Input } from '@angular/core';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';
import { BotaoComponent } from "../botao/botao.component";
import { ModalTrailerComponent } from '../modal-trailer/modal-trailer.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-filme-destaque',
  standalone: true,
  imports: [ BotaoComponent, BotaoComponent],
  templateUrl: './filme-destaque.component.html',
  styleUrl: './filme-destaque.component.scss'
})
export class FilmeDestaqueComponent {
  @Input() filme!: FilmeDescoberta
  readonly dialog = inject(MatDialog);

  abrirTrailer(event:boolean) {
    if (event) {
      const dialogRef = this.dialog.open(ModalTrailerComponent, {
        width: '1080px', height: '720px', data: {
          filme: this.filme
        }
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Dialog result: ${result}`);
      });
    }

  }
}
