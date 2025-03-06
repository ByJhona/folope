import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ResultadoPesquisaComponent } from './pages/resultado-pesquisa/resultado-pesquisa.component';
import { FilmeComponent } from './pages/filme/filme.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'pesquisar', component: ResultadoPesquisaComponent },
  { path: 'filme', component: FilmeComponent },
  { path: 'login', component: LoginComponent },
];
