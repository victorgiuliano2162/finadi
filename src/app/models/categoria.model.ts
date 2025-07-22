import { Movimentacao } from "./Movimentacao.model";
import { User } from "./usuario.model";

export interface Categoria {
  id?: string;
  nome: string;
  descricao?: string;
  usuario: User,
  movimentacoes?: Movimentacao[],
}