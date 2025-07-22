import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-categoria-dialog',
  templateUrl: './categoria-dialog.component.html',
  styleUrls: ['./categoria-dialog.component.css']
})
export class CategoriaDialogComponent {
  nome = '';
  descricao = '';

  constructor(
    private dialogRef: MatDialogRef<CategoriaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  salvar() {
    
    console.log('Categoria criada:', {
      nome: this.nome,
      descricao: this.descricao
    });
    this.dialogRef.close();
  }

  cancelar() {
    this.dialogRef.close();
  }
}
