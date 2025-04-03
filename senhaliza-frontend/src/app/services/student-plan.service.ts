import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StudentPlan } from '../models/student-plan';

@Injectable({
  providedIn: 'root'
})
export class StudentPlanService {

  servidor: string = "http://localhost:8080/api"
  recurso: string = "students_plans"

  constructor(private clienteHTTP: HttpClient) { }

  listarEstudiantePlanes(){

    return this.clienteHTTP.get<StudentPlan[]>(this.servidor + "/" + this.recurso);
  }

  buscarEstudiantePlan(id:number){
    return this.clienteHTTP.get<StudentPlan>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }

  actualizarEstudiantePlan(estudiantePlan: StudentPlan){
    return this.clienteHTTP.put<StudentPlan>(this.servidor + "/" + this.recurso+"/"+estudiantePlan.id.toString(),estudiantePlan);
  }

  registrarEstudiantePlan(estudiantePlan: StudentPlan){
    return this.clienteHTTP.post<StudentPlan>(this.servidor + "/" + this.recurso,estudiantePlan);
  }

  eliminarEstudiantePlan(id:number){
    return this.clienteHTTP.delete<StudentPlan>(this.servidor + "/" + this.recurso+"/"+id.toString());
  }}
