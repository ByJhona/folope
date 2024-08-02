import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { FilmeDescoberta } from '../types/FilmeDescoberta';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FilmeService {
  private apiUrl: string = 'http://localhost:8080/filme';
  constructor(private httpClient:HttpClient) { }

  listarFilmes():Observable<FilmeDescoberta[]>{
    return this.httpClient.get<FilmeDescoberta[]>(this.apiUrl+'/buscar');
  }


}
