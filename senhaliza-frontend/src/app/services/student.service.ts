import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  servidor: string = "http://localhost:8080/api"
  recurso: string = "students"

  constructor(private clienteHTTP: HttpClient) { }

  listarEstudiantes(){
    return this.clienteHTTP.get<Student[]>(this.servidor + "/" + this.recurso);
  }

  buscarEstudiante(id:number){
    return this.clienteHTTP.get<Student>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarEstudiante(student: Student){
    return this.clienteHTTP.put<Student>(this.servidor + "/" + this.recurso+"/"+student.id.toString(),student);
  }

  registrarEstudiante(student: Student){
    return this.clienteHTTP.post<Student>(this.servidor + "/" + this.recurso+"/register",student);
  }

  eliminarEstudiante(id:number){
    return this.clienteHTTP.delete<Student>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
