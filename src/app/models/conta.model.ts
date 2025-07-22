import { User } from './usuario.model';

export interface Conta {
  id?: string;
  name: string;
  banco: string;
  saldo: number;
  dataCriacao?: string | Date | null;
  usuario?: User;
}
