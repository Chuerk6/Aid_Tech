import { Component } from '@angular/core';
import { ExerciseService } from '../../../services/exercise.service';
import { Exercise } from '../../../models/exercise';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExerciseImageService } from '../../../services/exercise-image.service';
import { ExerciseImage } from '../../../models/exercise-image';
import { ImageService } from '../../../services/image.service';
import { Image } from '../../../models/image';
import { StudentExerciseService } from '../../../services/student-exercise.service';
import { StudentExercise } from '../../../models/student-exercise';

@Component({
  selector: 'app-question-to-complete',
  templateUrl: './question-to-complete.component.html',
  styleUrl: './question-to-complete.component.css'
})
export class QuestionToCompleteComponent {
  ejercicioActual: Exercise = { id:0, level: "", type_question: "", question: "", comment: "", lesson:{id:0, theme:"", description:""}};
  imagenActual: Image = {id: 0, link: "", meaning: ""};
  ejercicioImagenActual: ExerciseImage = {id: 0, correct_option: false, correct_answer: "none", 
    exercise: this.ejercicioActual, 
    image: this.imagenActual};
  preguntaForm!: FormGroup;
  respuestaCorrecta: boolean = false;
  respuestaEnviada: boolean = false;
  studentExercise: any;
  cargando: boolean = true;

  constructor(private exerciseService: ExerciseService, private formBuilder: FormBuilder, private ruta: ActivatedRoute, 
    private exerciseImageService: ExerciseImageService, private imageService: ImageService, private router: Router,
    private studentExerciseService: StudentExerciseService){}

  ngOnInit(){
    this.preguntaForm = this.formBuilder.group({
      answer: ['', [Validators.required, Validators.maxLength(20), Validators.minLength(1)]]
    });
    this.cargarPregunta();
  }

  enviarRespuesta(){
    if(!this.respuestaEnviada){     //al hacer el primer click (send answer)
      if(this.comprobarRespuesta()){
        this.respuestaCorrecta = true;
         //avisar al backend y obtener el sgt ejercicio solo si la respuesta fue correcta
        if(this.respuestaCorrecta){
          this.studentExerciseService.listarStudianteEjercicios().subscribe({
            next:(data: StudentExercise[])=>{
              const userId = localStorage.getItem("id");
              if(userId !== null){
                this.studentExercise = data.find(se => se.exercise.id === this.ejercicioActual.id && se.student.id === parseInt(userId));
                this.studentExercise.correct = true;
                this.studentExerciseService.actualizarStudianteEjercicio(this.studentExercise).subscribe({
                  next: (data)=>{
                    console.log("respuesta acualizada en la base de datos");
                  },
                  error: (err) => {
                    console.log(err);
                  }
                })
              }
            }
          })
        }
      }
      this.respuestaEnviada = true;
      this.preguntaForm.disable();
    }else{                          // al hacer el segundo click (reload or next)
      this.respuestaEnviada = false;
      this.preguntaForm.enable();
      this.preguntaForm.get("answer")?.reset();
      this.pasarAlSiguienteEjercicio();
    }
  }

  cargarPregunta(){
    this.respuestaCorrecta = false;
    const userId = localStorage.getItem("id");
    const lessonId = this.ruta.snapshot.params['codigo'];
    if(userId !== null){
    this.exerciseService.buscarEjercicioActual(parseInt(userId.toString()), parseInt(lessonId)).subscribe({
      next:(data: Exercise)=>{
        this.ejercicioActual = data;
        console.log(data);
      },
      error: (err)=>{
        console.log(err);
      }
    })
    }
    //buscar en la tabla exercises_images la imagen q le corresponde al ejercicio
    this.exerciseImageService.listarEjercicioImagenes().subscribe({
      next: (data: ExerciseImage[]) => {
        const eiTemp = data.find(ei => ei.exercise.id === this.ejercicioActual.id);
        if(eiTemp){
          this.ejercicioImagenActual = eiTemp;
          this.imageService.listarImagenes().subscribe({
            next:(dataImagenes: Image[])=>{
              const iTemp = dataImagenes.find(imagen => imagen.id==this.ejercicioImagenActual.image.id);
              if(iTemp) this.imagenActual = iTemp;
              this.cargando = false;
              console.log("Respuesta: ", this.imagenActual.meaning); //solo para ver la respuesta
            }
          })
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  comprobarRespuesta(){
    const respuestaIngresada = this.preguntaForm.get("answer")?.value.toString();
    if(respuestaIngresada){
      if(respuestaIngresada.toLowerCase() === this.imagenActual.meaning.toLowerCase()){
        return true;
      }
    }
    return false;
  }

  pasarAlSiguienteEjercicio(){
    const userId = localStorage.getItem("id");
    const lessonId = this.ruta.snapshot.params['codigo'];
    if(userId !== null){
      this.exerciseService.buscarEjercicioActual(parseInt(userId, 10), lessonId).subscribe({
        next:(data: Exercise)=>{
          if(data !== null){
            if(data.type_question === "Completar"){
              this.router.navigate(["/leccion/"+lessonId+"/ejercicio-completar"]).then(() => {this.cargarPregunta(); });
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
