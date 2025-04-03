import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Token } from '../models/token';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  servidor: string = "http://localhost:8080/api"
  recurso: string = "users"

  constructor(private clienteHTTP: HttpClient) { }

  listarUsuarios(){
    return this.clienteHTTP.get<User[]>(this.servidor + "/" + this.recurso);
  }

  buscarUsuario(id:number){
    return this.clienteHTTP.get<User>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarUsuario(usuario: User){
    return this.clienteHTTP.put<User>(this.servidor + "/" + this.recurso+"/"+usuario.id.toString(),usuario);
  }

  registrarUsuario(usuario: User){
    return this.clienteHTTP.post<User>(this.servidor + "/" + this.recurso+"/register",usuario);
  }

  eliminarUsuario(id:number){
    return this.clienteHTTP.delete<User>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  logearUsuario(usuario: User){
    return this.clienteHTTP.post<Token>(this.servidor+"/"+this.recurso+"/"+"login",usuario).pipe(
      tap((data:Token)=>{
        localStorage.setItem("jwtToken",data.jwtToken);
        localStorage.setItem("id",data.id.toString());
        localStorage.setItem("authorities",data.authorities);
      })
    );
  }

  deslogearUsuario(){
    localStorage.clear();
  }

  getId(){
    if(!(typeof localStorage === 'undefined')){
      return localStorage.getItem("id");
    };
    return null;
  }

  getToken(){
    if(!(typeof localStorage === 'undefined')){
      return localStorage.getItem("jwtToken");
    };
    return null;
  }

  getAuthorities(){
    if(!(typeof localStorage === 'undefined')){
      return localStorage.getItem("authorities");
    };
    return null;
  }
}
