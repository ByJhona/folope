import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ResultadoPesquisaComponent } from './pages/resultado-pesquisa/resultado-pesquisa.component';
import { NgModule } from '@angular/core';
import { FilmeComponent } from './pages/filme/filme.component';

export const routes: Routes = [
    { path: "", component: HomeComponent },
    { path: "pesquisar", component: ResultadoPesquisaComponent },
    { path: "filme", component: FilmeComponent }
];

