import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { FilmeDescoberta } from '../types/FilmeDescoberta';
import { Observable } from 'rxjs';
import { FilmesResponse } from '../types/FilmesResponse';
import { Filme } from '../types/Filme';


@Injectable({
  providedIn: 'root'
})
export class FilmeService {
  private apiUrl: string = 'http://localhost:8080/filme';
  constructor(private httpClient:HttpClient) { }

  listarFilmes():Observable<FilmeDescoberta[]>{
    return this.httpClient.get<FilmeDescoberta[]>(this.apiUrl+'/buscar');
  }

  pesquisarFilmeTitulo(titulo:string, numPagina:number):Observable<FilmesResponse>{
    return this.httpClient.get<FilmesResponse>(this.apiUrl+'/buscar/titulo?titulo='+titulo+"&pagina="+numPagina);

  }

  pesquisarFilmeId(id:number):Observable<Filme>{
    return this.httpClient.get<Filme>(this.apiUrl+'/buscar/id?id='+id);

  }


}
