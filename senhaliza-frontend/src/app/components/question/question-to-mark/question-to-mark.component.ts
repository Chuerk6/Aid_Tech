import { Component } from '@angular/core';
import { Exercise } from '../../../models/exercise';
import { FormBuilder, FormGroup, Validators, FormsModule } from '@angular/forms';
import { ExerciseService } from '../../../services/exercise.service'; 
import { ExerciseImageService } from '../../../services/exercise-image.service';
import { ExerciseImage } from '../../../models/exercise-image';
import { Image } from '../../../models/image';
import { ImageService } from '../../../services/image.service';
import {MatRadioModule} from '@angular/material/radio';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentExerciseService } from '../../../services/student-exercise.service';
import { StudentExercise } from '../../../models/student-exercise';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@Component({
  selector: 'app-question-to-mark',
  templateUrl: './question-to-mark.component.html',
  styleUrl: './question-to-mark.component.css'
})
export class QuestionToMarkComponent {
  ejercicioActual:        Exercise = { id:0, level: "", type_question: "", question: "", comment: "", lesson:{id:0, theme:"", description:""}};
  imagenCorrecta:           Image = {id: 0, link: "", meaning: ""};
  ejercicioImagenActual:  ExerciseImage[] = [];
  respuestaCorrecta: boolean = false;
  respuestaEnviada: boolean = false;
  opcionesImagenes: Image[] = [];
  selectedImageId!: number;
  studentExercise : any;
  cargando: boolean = true;

  constructor(private exerciseImageService: ExerciseImageService, 
    private imageService: ImageService, 
    private formBuilder: FormBuilder,
    private ruta: ActivatedRoute,
    private exerciseService: ExerciseService,
    private router: Router,
    private studnetExerciseService: StudentExerciseService
  ){}

  ngOnInit(){
    this.cargarPregunta();
  }

  cargarPregunta(){
    //se busca el ejercicio actual
    const userId = localStorage.getItem("id");
    const lessonId = this.ruta.snapshot.params['codigo'];
    if(userId !== null){
      console.log("id usuario: ", userId, "id leccion: ", lessonId);
      this.exerciseService.buscarEjercicioActual(parseInt(userId.toString()), lessonId).subscribe({
        next:(data: Exercise)=>{
          this.ejercicioActual = data;
          console.log("la data es: ", data);
        }
      })
    }

    
    // buscar en la tabla exercises_images la imagen q le corresponde al ejercicio actual
    this.exerciseImageService.listarEjercicioImagenes().subscribe({
      next: (data: ExerciseImage[]) => {
        const eiTemp = data.filter(ei => ei.exercise.id === this.ejercicioActual.id);
        if(eiTemp){
          this.ejercicioImagenActual = eiTemp;
          console.log(eiTemp);
          this.imageService.listarImagenes().subscribe({
            next:(dataImagenes: Image[])=>{
              const iTemp = dataImagenes.filter(imagen => this.ejercicioImagenActual.some(imagenejercicio => imagenejercicio.image.id===imagen.id));
              this.opcionesImagenes.push(...iTemp);
              console.log(this.opcionesImagenes);
            },
            error: (err)=>{
              console.log(err);
            }
          })
          
          // se busca la opcion correcta
          const imgTemp = this.ejercicioImagenActual.find(ei => ei.correct_option===true)?.image;
          if(imgTemp){
            this.imagenCorrecta=imgTemp;
          }
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  enviarRespuesta(){
    if(!this.respuestaEnviada){
      if(this.selectedImageId.toString() === this.imagenCorrecta.id.toString()){ // se verifica si la respuesta es correcta (seleccionada=correcta)
        this.respuestaCorrecta = true;  
        //avisar al backend y obtener el sgt ejercicio
        this.studnetExerciseService.listarStudianteEjercicios().subscribe({
          next:(data: StudentExercise[])=>{
            const userId = localStorage.getItem("id");
            if(userId !== null){
              this.studentExercise = data.find(se => se.exercise.id === this.ejercicioActual.id && se.student.id === parseInt(userId));
              this.studentExercise.correct = true;
              this.studnetExerciseService.actualizarStudianteEjercicio(this.studentExercise).subscribe({
                next: (data)=>{
                  this.pasarAlSiguienteEjercicio();
                },
                error: (err) => {
                  console.log(err);
                }
              })
            }
          }
        })
        
      }
      this.respuestaEnviada = true;
    }else{
     
      this.respuestaEnviada = false;
    }
  }

  pasarAlSiguienteEjercicio(){
    const userId = localStorage.getItem("id");
    const lessonId = this.ruta.snapshot.params['codigo'];
    if(userId !== null){
      this.exerciseService.buscarEjercicioActual(parseInt(userId, 10), lessonId).subscribe({
        next:(data: Exercise)=>{
          if(data !== null){
            console.log(data);
            if(data.type_question === "Completar"){
              this.router.navigate(["/leccion/"+lessonId+"/ejercicio-completar"]);
            }
            if(data.type_question === "Marcar"){
              this.router.navigate(["/leccion/"+lessonId+"/ejercicio-marcar"]).then(() => {this.cargarPregunta(); });
            }
          }else{console.log("fin de la leccion"); this.router.navigate(["/estudiante-lecciones"]);}
        },
        error:(err)=>{
          console.log(err);
        }
      })
    }
  }
}
