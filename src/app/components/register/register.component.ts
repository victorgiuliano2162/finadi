import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  mainForm!: FormGroup;

  constructor(private _formBuilder: FormBuilder) {}

  ngOnInit() {
    // Construímos a estrutura aninhada do formulário para corresponder ao HTML
    this.mainForm = this._formBuilder.group({
      // 1. FormGroup para o primeiro passo
      dadosPessoais: this._formBuilder.group({
        // Alterado para corresponder ao formControlName="firstCtrl" do HTML
        firstCtrl: ['', Validators.required]
      }),
      // 2. FormGroup para o segundo passo
      informacoesContato: this._formBuilder.group({
        // Alterado para corresponder ao formControlName="secondCtrl" do HTML
        secondCtrl: ['', Validators.required]
      })
      // O grupo 'endereco' foi removido pois não é usado no seu HTML.
    });
  }

  // Getter para facilitar o acesso ao FormGroup do primeiro passo
  get dadosPessoais(): FormGroup {
    return this.mainForm.get('dadosPessoais') as FormGroup;
  }

  // Getter para facilitar o acesso ao FormGroup do segundo passo
  get informacoesContato(): FormGroup {
    return this.mainForm.get('informacoesContato') as FormGroup;
  }

  // Função para ser chamada na submissão do formulário
  onSubmit(): void {
    if (this.mainForm.valid) {
      console.log("Formulário enviado com sucesso!");
      console.log(this.mainForm.value);
      // Exemplo de como acessar os valores:
      // const nome = this.mainForm.value.dadosPessoais.firstCtrl;
      // const endereco = this.mainForm.value.informacoesContato.secondCtrl;
      alert('Formulário enviado! Verifique o console.');
    } else {
      alert('Formulário inválido. Por favor, preencha todos os campos.');
    }
  }
}