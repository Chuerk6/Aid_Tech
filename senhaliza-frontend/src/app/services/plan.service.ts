import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Plan } from '../models/plan';

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "plans"

  constructor(private clienteHTTP: HttpClient) { }

  listarPlanes(){

    return this.clienteHTTP.get<Plan[]>(this.servidor + "/" + this.recurso);
  }

  buscarPlan(id:number){
    return this.clienteHTTP.get<Plan>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarPlan(plan: Plan){
    return this.clienteHTTP.put<Plan>(this.servidor + "/" + this.recurso+"/"+plan.id.toString(),plan);
  }

  registrarPlan(plan: Plan){
    return this.clienteHTTP.post<Plan>(this.servidor + "/" + this.recurso,plan);
  }

  eliminarPlan(id:number){
    return this.clienteHTTP.delete<Plan>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }}
