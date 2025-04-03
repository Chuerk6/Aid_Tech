import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Exercise } from '../models/exercise';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "exercises"

  constructor(private clienteHTTP: HttpClient) { }

  listarEjercicios(){

    return this.clienteHTTP.get<Exercise[]>(this.servidor + "/" + this.recurso);
  }

  buscarEjercicio(id:number){
    return this.clienteHTTP.get<Exercise>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarEjercicio(ejercicio: Exercise){
    return this.clienteHTTP.put<Exercise>(this.servidor + "/" + this.recurso+"/"+ejercicio.id.toString(),ejercicio);
  }

  registrarEjercicio(ejercicio: Exercise){
    return this.clienteHTTP.post<Exercise>(this.servidor + "/" + this.recurso,ejercicio);
  }

  eliminarEjercicio(id:number){
    return this.clienteHTTP.delete<Exercise>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  buscarEjercicioActual(id_user: number, id_lesson: number){
    return this.clienteHTTP.get<Exercise>(this.servidor + "/" + this.recurso + "/" + id_user.toString() + "/" + id_lesson.toString());
  }
}
