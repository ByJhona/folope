import { ChangeDetectionStrategy, Component, inject, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ModalTrailerComponent } from '../modal-trailer/modal-trailer.component';
import { FilmeDescoberta } from '../../types/FilmeDescoberta';

@Component({
  selector: 'app-bnt-trailer',
  standalone: true,
  imports: [MatButtonModule, MatDialogModule],
  templateUrl: './bnt-trailer.component.html',
  styleUrl: './bnt-trailer.component.scss'
})
export class BntTrailerComponent {
  @Input() filme!: FilmeDescoberta
  readonly dialog = inject(MatDialog);

  openDialog() {
    const dialogRef = this.dialog.open(ModalTrailerComponent, {width: '1080px', height:'720px',data:{
      filme: this.filme
    }});

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
