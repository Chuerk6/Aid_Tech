import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Level } from '../models/level';

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "levels"

  constructor(private clienteHTTP: HttpClient) { }

  listarNiveles(){

    return this.clienteHTTP.get<Level[]>(this.servidor + "/" + this.recurso);
  }

  buscarNivel(id:number){
    return this.clienteHTTP.get<Level>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarNivel(nivel: Level){
    return this.clienteHTTP.put<Level>(this.servidor + "/" + this.recurso+"/"+nivel.id.toString(),nivel);
  }

  registrarNivel(nivel: Level){
    return this.clienteHTTP.post<Level>(this.servidor + "/" + this.recurso,nivel);
  }

  eliminarNivel(id:number){
    return this.clienteHTTP.delete<Level>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
