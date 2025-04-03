import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Lesson } from '../models/lesson';

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "lessons"

  constructor(private clienteHTTP: HttpClient) { }

  listarLecciones(){

    return this.clienteHTTP.get<Lesson[]>(this.servidor + "/" + this.recurso);
  }

  buscarLeccion(id:number){
    return this.clienteHTTP.get<Lesson>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarLeccion(leccion: Lesson){
    return this.clienteHTTP.put<Lesson>(this.servidor + "/" + this.recurso+"/"+leccion.id.toString(),leccion);
  }

  registrarLeccion(leccion: Lesson){
    return this.clienteHTTP.post<Lesson>(this.servidor + "/" + this.recurso,leccion);
  }

  eliminarLeccion(id:number){
    return this.clienteHTTP.delete<Lesson>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
