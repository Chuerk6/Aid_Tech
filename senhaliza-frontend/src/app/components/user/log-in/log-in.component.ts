import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../../models/user';
import { Token } from '../../../models/token';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {
  @Output() usuarioLogeado = new EventEmitter<void>();
  @Output() registrarse = new EventEmitter<void>();
  @Output() landingPage = new EventEmitter<void>();
  logeoForm!:FormGroup;
  muestraPassword: boolean=false;

  constructor (private ruta:ActivatedRoute, private userService:UserService,
    private formBuilder:FormBuilder, private enrutador:Router, 
    private _snackBar: MatSnackBar) {}

  ngOnInit(){
    this.crearLogeoForm();
  }

  crearLogeoForm(){
    this.logeoForm = this.formBuilder.group({
      userName:["",[Validators.minLength(3),Validators.required]],
      password:["",[Validators.minLength(3),Validators.required]]
    })
  }

  logearUsuario(){
    const user: User = {
      id:0,
      userName:this.logeoForm.get("userName")!.value,
      password:this.logeoForm.get("password")!.value,
      type: ""
    };

    this.userService.logearUsuario(user).subscribe({
      next:(data:Token)=>{        
        console.log(data);
        if(data.authorities === "ROLE_STUDENT")
          this.enrutador.navigate(["/estudiante-perfil"]);
        else 
          this.enrutador.navigate(["/dashboard"]);
        this.usuarioLogeado.emit();
      },
      error:(err)=>{
        console.log(err);
        this._snackBar.open("Error en el ingreso: "+err.error.message,"OK",{duration:3000});
      }
    })
  }

  irARegistrar(){
    this.registrarse.emit();  
  }
  irALandingPage(){
    this.landingPage.emit();
  }

}
