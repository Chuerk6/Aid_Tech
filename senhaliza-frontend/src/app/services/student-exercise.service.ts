import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StudentExercise } from '../models/student-exercise';

@Injectable({
  providedIn: 'root'
})
export class StudentExerciseService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "students_exercises"

  constructor(private clienteHTTP: HttpClient) { }

  listarStudianteEjercicios(){

    return this.clienteHTTP.get<StudentExercise[]>(this.servidor + "/" + this.recurso);
  }

  buscarStudianteEjercicio(id:number){
    return this.clienteHTTP.get<StudentExercise>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarStudianteEjercicio(estudianteEjercicio: StudentExercise){
    return this.clienteHTTP.put<StudentExercise>(this.servidor + "/" + this.recurso+"/"+estudianteEjercicio.id.toString(),estudianteEjercicio);
  }

  registrarStudianteEjercicio(estudianteEjercicio: StudentExercise){
    return this.clienteHTTP.post<StudentExercise>(this.servidor + "/" + this.recurso,estudianteEjercicio);
  }

  eliminarStudianteEjercicio(id:number){
    return this.clienteHTTP.delete<StudentExercise>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
