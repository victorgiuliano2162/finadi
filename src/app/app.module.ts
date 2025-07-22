import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './components/footer/footer.component';
import { ToolBarComponent } from './components/tool-bar/tool-bar.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import {MaterialModule} from "./material.module";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { provideNgxMask } from 'ngx-mask';
import { OnlyNumbersDirective } from './directive/only-numbers.directive';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { ContaComponent } from './components/conta/conta.component';
import { NgChartsModule  } from 'ng2-charts';
import { CategoriaDialogComponent } from './components/categoria-dialog/categoria-dialog.component';
import { MovimentacaoDialogComponent } from './components/movimentacao-dialog/movimentacao-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ToolBarComponent,
    SidenavComponent,
    LoginPageComponent,
    RegisterComponent,
    OnlyNumbersDirective,
    DashboardComponent,
    ContaComponent,
    CategoriaDialogComponent,
    MovimentacaoDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [
    provideNgxMask(),
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
