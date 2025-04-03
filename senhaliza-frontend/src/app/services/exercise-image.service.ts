import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExerciseImage } from '../models/exercise-image';

@Injectable({
  providedIn: 'root'
})
export class ExerciseImageService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "exercises_images"

  constructor(private clienteHTTP: HttpClient) { }

  listarEjercicioImagenes(){

    return this.clienteHTTP.get<ExerciseImage[]>(this.servidor + "/" + this.recurso);
  }

  buscarEjercicioImagen(id:number){
    return this.clienteHTTP.get<ExerciseImage>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarEjercicioImagen(ejercicioImagen: ExerciseImage){
    return this.clienteHTTP.put<ExerciseImage>(this.servidor + "/" + this.recurso+"/"+ejercicioImagen.id.toString(),ejercicioImagen);
  }

  registrarEjercicioImagen(ejercicioImagen: ExerciseImage){
    return this.clienteHTTP.post<ExerciseImage>(this.servidor + "/" + this.recurso,ejercicioImagen);
  }

  eliminarEjercicioImagen(id:number){
    return this.clienteHTTP.delete<ExerciseImage>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
