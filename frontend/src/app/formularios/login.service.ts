import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  loginForm: FormGroup = new FormGroup({
    nome: new FormControl('', Validators.required),
    senha: new FormControl('', Validators.required),
  });

  obterNome() {
    return this.loginForm.value.nome;
  }

  obterSenha() {
    return this.loginForm.value.senha;
  }
}
