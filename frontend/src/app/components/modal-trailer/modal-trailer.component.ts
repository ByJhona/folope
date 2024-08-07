import {ChangeDetectionStrategy, Component, Inject, inject} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialog, MatDialogModule} from '@angular/material/dialog';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';

@Component({
  selector: 'app-modal-trailer',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule],
  templateUrl: './modal-trailer.component.html',
  styleUrl: './modal-trailer.component.scss'
})
export class ModalTrailerComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: {filme:FilmeDescoberta}){}

}
