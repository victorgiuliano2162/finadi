import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn, Router } from '@angular/router';
import { Observable, catchError, EMPTY } from 'rxjs';
import { ContaService } from '../conta/conta.service';
import { Conta } from 'src/app/models/conta.model';


export const contaResolver: ResolveFn<Conta> = (
  route: ActivatedRouteSnapshot, // Fornece acesso aos parâmetros da rota
): Observable<Conta> => {
  
  // 1. Injeta os serviços necessários
  const contaService = inject(ContaService);
  const router = inject(Router);

  // 2. Pega o parâmetro 'id' da URL (ex: /contas/acc-uuid-001)
  const contaId = route.paramMap.get('id');

  // 3. Se não houver ID, redireciona e cancela a navegação
  if (!contaId) {
    router.navigate(['/dashboard']); // Redireciona para uma página segura
    return EMPTY; // Retorna um Observable vazio para cancelar
  }

  // 4. Chama o serviço para buscar os dados e retorna o Observable
  return contaService.getContaById(contaId).pipe(
    catchError(() => {
      // Se ocorrer um erro (ex: conta não encontrada), redireciona
      alert('Conta não encontrada!');
      router.navigate(['/dashboard']);
      return EMPTY; // Cancela a navegação
    })
  );
};
