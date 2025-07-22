import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ChartData, ChartOptions } from 'chart.js';
import { Conta } from 'src/app/models/conta.model';
import { CategoriaDialogComponent } from '../categoria-dialog/categoria-dialog.component';
import { MovimentacaoDialogComponent } from '../movimentacao-dialog/movimentacao-dialog.component';
import { AuthService } from 'src/app/service/shared/auth.service';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/usuario.model';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css'],
})
export class ContaComponent {
  conta!: Conta;
  currentUser$: Observable<User | null>;
  constructor(private route: ActivatedRoute, private dialog: MatDialog, private authService: AuthService) {
    this.currentUser$ = this.authService.currentUser$;
  }

  ngOnInit(): void {
    // Os dados resolvidos estão disponíveis em 'this.route.snapshot.data'
    // A chave 'dadosDaConta' é a mesma que você definiu no ficheiro de rotas.
    this.conta = this.route.snapshot.data['conta'];

    // É só isso! Não precisa de .subscribe() nem de chamadas a serviços aqui.
    console.log('Dados da conta pré-carregados pelo Resolver:', this.conta);
  }

  abrirDialogCategoria(): void {
  this.dialog.open(CategoriaDialogComponent, {
    width: '400px',
    data: {} // Você pode passar dados se quiser editar futuramente
  });
}

abrirDialogTransacao(): void {
  this.dialog.open(MovimentacaoDialogComponent, {
    width: '450px',
    data: {} // se quiser enviar dados para edição futura
  });
}


graficoResumoData: ChartData<'doughnut', number[], string> = {
  labels: ['Crédito', 'Débito'],
  datasets: [
    {
      data: [3500, 2200],
      backgroundColor: ['#4CAF50', '#F44336']
    }
  ]
};

graficoResumoOptions: ChartOptions<'doughnut'> = {
  responsive: true,
  plugins: {
    legend: { position: 'bottom' }
  }
};

graficoHistoricoData: ChartData<'line', number[], string> = {
  labels: ['Jan', 'Feb', 'Mar', 'Apr'],
  datasets: [
    {
      label: 'Crédito',
      data: [1000, 2500, 1800, 3000],
      borderColor: '#4CAF50',
      fill: false,
      tension: 0.3
    },
    {
      label: 'Débito',
      data: [800, 1900, 1200, 2700],
      borderColor: '#F44336',
      fill: false,
      tension: 0.3
    }
  ]
};

graficoHistoricoOptions: ChartOptions<'line'> = {
  responsive: true,
  plugins: {
    legend: { position: 'top' }
  }
};


}
