import { Component } from '@angular/core';
import { LessonService } from '../../../services/lesson.service';
import { Lesson } from '../../../models/lesson';
import { ExerciseService } from '../../../services/exercise.service';
import { Exercise } from '../../../models/exercise';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-lesson',
  templateUrl: './student-lesson.component.html',
  styleUrl: './student-lesson.component.css'
})
export class StudentLessonComponent {
  lessons!: Lesson[];
  les!: Lesson;
  constructor(private lessonService: LessonService, private exerciseService: ExerciseService, private router: Router){}
  
  ngOnInit(){
    this.lessonService.listarLecciones().subscribe({
      next:(data: Lesson[])=>{
        this.lessons = data;
      },
      error:(err)=>{
        console.log(err);
      }
    });
  }

  irAEjercicio(lessonId: number){
    const userId = localStorage.getItem("id");
    if(userId !== null){
      this.exerciseService.buscarEjercicioActual(parseInt(userId, 10), lessonId).subscribe({
        next:(data: Exercise)=>{
          if(data !== null){
            if(data.type_question === "Completar"){
              this.router.navigate(["/leccion/"+lessonId+"/ejercicio-completar"]);
            }
            if(data.type_question === "Marcar"){
              this.router.navigate(["/leccion/"+lessonId+"/ejercicio-marcar"]);
            }
          }else{console.log("fin de la leccion"); alert("esta leccion esta completada"); this.router.navigate(["/estudiante-lecciones"]);}
        },
        error:(err)=>{
          console.log(err);
        }
      })
    }
  }
}
