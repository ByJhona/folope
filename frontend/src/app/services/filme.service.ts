import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class FilmeService {
  private apiUrl: string = 'http://localhost:8080/filme';
  constructor(private httpClient:HttpClient) { }

  listarFilmes(){
    return this.httpClient.get(this.apiUrl+'/buscar');
  }


}
