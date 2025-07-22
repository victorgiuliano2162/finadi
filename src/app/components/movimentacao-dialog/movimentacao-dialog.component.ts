import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-movimentacao-dialog',
  templateUrl: './movimentacao-dialog.component.html',
  styleUrls: ['./movimentacao-dialog.component.css']
})
export class MovimentacaoDialogComponent {
  descricao = '';
  valor: number = 0;
  data: Date | null = null;

  constructor(
    private dialogRef: MatDialogRef<MovimentacaoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dataInject: any
  ) {}

  salvar() {
    console.log('Movimentação criada:', {
      descricao: this.descricao,
      valor: this.valor,
      data: this.data
    });
    this.dialogRef.close();
  }

  cancelar() {
    this.dialogRef.close();
  }
}
