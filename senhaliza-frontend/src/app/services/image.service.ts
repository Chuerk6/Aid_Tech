import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Image } from '../models/image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "images"

  constructor(private clienteHTTP: HttpClient) { }

  listarImagenes(){

    return this.clienteHTTP.get<Image[]>(this.servidor + "/" + this.recurso);
  }

  buscarImagen(id:number){
    return this.clienteHTTP.get<Image>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarImagen(imagen: Image){
    return this.clienteHTTP.put<Image>(this.servidor + "/" + this.recurso+"/"+imagen.id.toString(),imagen);
  }

  registrarImagen(imagen: Image){
    return this.clienteHTTP.post<Image>(this.servidor + "/" + this.recurso,imagen);
  }

  eliminarImagen(id:number){
    return this.clienteHTTP.delete<Image>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }
}
