import { HttpClient } from '@angular/common/http';
import { LessonStudent } from '../models/lesson-student';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LessonStudentService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "lesson_students"

  constructor(private clienteHTTP: HttpClient) { }

  listarLeccionEstudiantes(){

    return this.clienteHTTP.get<LessonStudent[]>(this.servidor + "/" + this.recurso);
  }

  buscarLeccionEstudiante(id:number){
    return this.clienteHTTP.get<LessonStudent>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarLeccionEstudiante(leccionEstudiante: LessonStudent){
    return this.clienteHTTP.put<LessonStudent>(this.servidor + "/" + this.recurso+"/"+leccionEstudiante.id.toString(),leccionEstudiante);
  }

  registrarLeccionEstudiante(leccionEstudiante: LessonStudent){
    return this.clienteHTTP.post<LessonStudent>(this.servidor + "/" + this.recurso,leccionEstudiante);
  }

  eliminarLeccionEstudiante(id:number){
    return this.clienteHTTP.delete<LessonStudent>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
