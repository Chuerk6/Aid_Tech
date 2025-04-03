import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Authority } from '../models/authority';

@Injectable({
  providedIn: 'root'
})
export class AuthorityService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "authoritys"

  constructor(private clienteHTTP: HttpClient) { }

  listarAutoridades(){

    return this.clienteHTTP.get<Authority[]>(this.servidor + "/" + this.recurso);
  }

  buscarAutoridad(id:number){
    return this.clienteHTTP.get<Authority>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarAutoridad(autoridad: Authority){
    return this.clienteHTTP.put<Authority>(this.servidor + "/" + this.recurso+"/"+autoridad.id.toString(),autoridad);
  }

  registrarAutoridad(autoridad: Authority){
    return this.clienteHTTP.post<Authority>(this.servidor + "/" + this.recurso,autoridad);
  }

  eliminarAutoridad(id:number){
    return this.clienteHTTP.delete<Authority>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
