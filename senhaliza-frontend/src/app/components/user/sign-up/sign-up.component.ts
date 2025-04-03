import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../../models/user';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css',
})
export class SignUpComponent {
  tipoUsuario: string = 'ROLE_STUDENT';
  signUpForm!: FormGroup;
  muestraPassword: boolean = false;
  muestraPasswordConfirm: boolean = false;
  @Output() logearse = new EventEmitter<void>();
  @Output() landingPage = new EventEmitter<void>();

  constructor(
    private usuarioService: UserService,
    private formBuilder: FormBuilder,
    private _snackbar: MatSnackBar
  ) {}

  ngOnInit(){
    this.crearSignUpForm();
  }

  crearSignUpForm(){
    this.signUpForm = this.formBuilder.group({
      userName:["",[Validators.minLength(3), Validators.required]],
      password:["",[Validators.minLength(3), Validators.required]],
      confirmPassword:["",[Validators.minLength(3), Validators.required]]
    })
  }

  registrarUsuario(){
    const usuario: User={
      id: 0,
      userName: this.signUpForm.get("userName")!.value,
      password: this.signUpForm.get("password")!.value,
      type: this.tipoUsuario
    };
    this.usuarioService.registrarUsuario(usuario).subscribe({
      next:(data)=>{
        this._snackbar.open("¡El usuario se registro con exito!","Ok",{duration:2000});
        this.logearse.emit();
      },
      error:(err)=>{
        console.log(err);
        this._snackbar.open("No se registro el usuario: " + err.error.message,"Ok",{duration:3000});
      }
    })
  }

  irALogear(){
    this.logearse.emit();
  }
  irALandingPage(){
    this.landingPage.emit();
  }
}
