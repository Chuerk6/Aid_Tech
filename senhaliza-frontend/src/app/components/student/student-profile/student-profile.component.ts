import { Component } from '@angular/core';
import { Student } from '../../../models/student';
import { User } from '../../../models/user';
import { StudentService } from '../../../services/student.service';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css'
})
export class StudentProfileComponent {
  usuario: User = {id: 0, userName: '', password: '', type: ''};
  idUsuario: any;
  estudiante: Student = {id: 0, name: "", email: "", phone: "", avatar: "", level: { id: 0, name: "", description: "" }, user: {id: 0, userName: "", password: "", type: ""}};


  constructor(private studentService: StudentService, private userService: UserService){}

  ngOnInit(){
    
    if(typeof localStorage !== 'undefined'){
      this.idUsuario = localStorage.getItem("id");
    }

    if (this.idUsuario) {
      this.userService.buscarUsuario(parseInt(this.idUsuario)).subscribe({
        next: (data: User) => {
          this.usuario = data;
          this.cargarPerfilEstudiante();
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }

  cargarPerfilEstudiante() {
    this.studentService.listarEstudiantes().subscribe({
      next: (data: Student[]) => {
        const estudianteEncontrado = data.find(est => est.user.id === this.usuario.id);
        if (estudianteEncontrado) {
          this.estudiante = estudianteEncontrado;
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}