import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Conta } from 'src/app/models/conta.model';
import { User } from 'src/app/models/usuario.model';
import { ContaService } from 'src/app/service/conta/conta.service';
import { AuthService } from 'src/app/service/shared/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  currentUser$: Observable<User | null>;
  contas: Conta[] = [];
  currentUser: User | null = null;
  private authSubscription: Subscription;

  ngOnInit(): void {
    // Chama o método para buscar as contas do usuário ao inicializar o componente
    this.authSubscription = this.authService.currentUser$.subscribe((user) => {
      // 2. Atribui o valor a uma propriedade local
      this.currentUser = user;

      // 3. Executa a lógica necessária com os dados do usuário
      if (user) {
        console.log('!!!!!!!!!Utilizador logado:', user.id);
        // Exemplo: this.carregarPedidosDoUsuario(user.id);
      }
    });
    this.getContasByUserId();
  }
  constructor(
    private authService: AuthService,
    private contaService: ContaService
  ) {
    // Atribua o observable do serviço à sua propriedade local
    this.currentUser$ = this.authService.currentUser$;
  }

  contaForm: Conta = {
    name: '',
    banco: '',
    saldo: 0,
    dataCriacao: null,
  };

  getContasByUserId() {
    this.contaService.getContasByUserId(this.currentUser.id).subscribe({
      // 'next' é chamado quando a API responde com sucesso
      next: (dadosRecebidos) => {
        this.contas = dadosRecebidos; // Armazena os dados na propriedade do componente
        console.log('Contas carregadas:', this.contas);
      },
      // 'error' é chamado se a API retornar um erro
      error: (erro) => {
        console.error('Falha ao buscar contas:', erro);
        alert('Não foi possível carregar as contas.');
      },
    });
  }

  submitContaForm(form: NgForm): void {
    // --- 1. Validação ---
    if (form.invalid) {
      alert('Formulário inválido! Por favor, preencha todos os campos.');
      return;
    }
    // Garanta que o utilizador está logado
    if (!this.currentUser) {
      alert('Erro: Utilizador não está logado.');
      return;
    }
    // Garanta que uma data foi selecionada
    if (!this.contaForm.dataCriacao) {
      alert('Por favor, selecione uma data de criação.');
      return;
    }

    // --- 2. Processamento da Data ---
    // this.contaForm.dataCriacao JÁ É UM OBJETO DATE! Não precisa de new Date().
    // Apenas o formatamos para a string que a API espera.
    //const dataFormatada = this.formatarDataParaLocalDateTime(this.contaForm.dataCriacao);

    // --- 3. Montagem do Payload ---
    // Crie o objeto final para enviar, usando os dados do formulário e a data formatada
    const payloadParaAPI: Conta = {
      name: this.contaForm.name,
      banco: this.contaForm.banco,
      saldo: this.contaForm.saldo,
      dataCriacao: this.contaForm.dataCriacao,
      usuario: this.currentUser, // Supondo que você tenha o objeto do utilizador
    };

    // --- 4. Chamada ao Serviço ---
    console.log('Enviando para a API:', payloadParaAPI);
    this.contaService.createNewAccount(payloadParaAPI).subscribe({
      next: (res: Conta) => {
        console.log('Conta criada com sucesso', res);
        alert('Conta criada com sucesso!');
        this.getContasByUserId(); // Atualiza a lista de contas
        form.resetForm(); // Limpa o formulário
      },
      error: (err: any) => {
        alert('Erro ao criar conta');
        console.error(err);
      },
    });
  }

  // A sua função auxiliar está perfeita, não precisa de mudar.
  private formatarDataParaLocalDateTime(data: Date): string {
    if (!data) {
      return '';
    }
    // toISOString() gera "2025-07-22T03:00:00.000Z" (em UTC)
    // Usamos .slice(0, 19) para cortar e obter apenas "2025-07-22T03:00:00"
    return data.toISOString().slice(0, 19);
  }

  bancos: string[] = [
    'Banco do Brasil',
    'Bradesco',
    'BTG Pactual',
    'C6 Bank',
    'Caixa Econômica Federal',
    'Inter',
    'Itaú Unibanco',
    'Nubank',
    'Safra',
    'Santander',
    'Sicoob',
    'Sicredi',
    'Votorantim',
    'XP Inc.',
  ];
}
