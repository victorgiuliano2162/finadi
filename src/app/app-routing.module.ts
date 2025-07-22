import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import { RegisterComponent } from "./components/register/register.component";
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ContaComponent } from './components/conta/conta.component';
import { contaResolver } from './service/resolver/conta.resolver';

const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: '',
    component: LoginPageComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'conta/:id',
    component: ContaComponent,
    resolve: {
      conta: contaResolver // Usando o resolver para carregar a conta antes de ativar o componente
    }
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
