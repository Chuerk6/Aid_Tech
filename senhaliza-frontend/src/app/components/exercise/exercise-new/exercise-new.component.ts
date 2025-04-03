import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExerciseService } from '../../../services/exercise.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Exercise } from '../../../models/exercise';
import { Router } from '@angular/router';
import { ServerError } from '../../../models/server-error';
import { Lesson } from '../../../models/lesson';
import { LessonService } from '../../../services/lesson.service';

@Component({
  selector: 'app-exercise-new',
  templateUrl: './exercise-new.component.html',
  styleUrl: './exercise-new.component.css'
})
export class ExerciseNewComponent {
  @Input() idExercise!: number;
  @Output() register = new EventEmitter<void>();
  detalleForm!: FormGroup;
  noExiste: boolean = false;
  lecciones: Lesson[]=[];

  constructor(
    private exerciseService: ExerciseService,
    private formBuilder: FormBuilder,
    private enrutador: Router,
    private _snackBar: MatSnackBar,
    private lessonService: LessonService
  ) {}

  ngOnInit() {
    this.cargaLecciones();
    this.cargaExercise();
  }
  
  cargaLecciones(){
    this.lessonService.listarLecciones().subscribe({
      next:(data:Lesson[])=>{
        this.lecciones = data;
      }
    })
  }

  cargaExercise() {
    this.detalleForm = this.formBuilder.group({
      id: [''],
      level: ['',[Validators.required, Validators.maxLength(100), Validators.minLength(1)]],
      type_question: ['',[Validators.required, Validators.maxLength(10), Validators.minLength(1)]],
      question: ['',[Validators.required, Validators.maxLength(500), Validators.minLength(1)]],
      comment: ['',[Validators.required, Validators.maxLength(500), Validators.minLength(1)]],
      lesson: ['',[Validators.required]]
    });

    //this.idExercise = this.ruta.snapshot.params['codigo'];

    if (this.idExercise != 0 && this.idExercise != undefined) {
      this.exerciseService.buscarEjercicio(this.idExercise).subscribe({
        next: (data: Exercise) => {
          this.detalleForm.get('id')?.setValue(data.id);
          this.detalleForm.get('level')?.setValue(data.level);
          this.detalleForm.get('type_question')?.setValue(data.type_question);
          this.detalleForm.get('question')?.setValue(data.question);
          this.detalleForm.get('comment')?.setValue(data.comment);
          this.detalleForm.get('lesson')?.setValue(data.lesson.id);
        },
        error: (err: ServerError) => {
          if (err.status == 404) {
            this.noExiste = true;
          }
          console.log(err);
        },
      });
    } else {
      this.idExercise = 0;
      this.detalleForm.get('id')?.setValue(this.idExercise);
    }
  }
  
  registrarEjercicio() {
    const exercise: Exercise = {
      id: parseInt(this.detalleForm.get('id')!.value),
      level: this.detalleForm.get('level')!.value,
      type_question: this.detalleForm.get('type_question')!.value,
      question: this.detalleForm.get("question")!.value,
      comment: this.detalleForm.get("comment")!.value,
      lesson: { id: this.detalleForm.get("lesson")!.value, theme: "", description: ""}
    };
    if (this.idExercise != 0) {
      this.exerciseService.actualizarEjercicio(exercise).subscribe({
        next: () => {
          this._snackBar.open('El ejercicio se actualizó', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-ejercicios']);
          console.log("se enruto a la lista");
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.exerciseService.registrarEjercicio(exercise).subscribe({
        next: () => {
          this._snackBar.open('El ejercicio se registró', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-ejercicios']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    this.register.emit();
  }

  cancelar(){ this.register.emit(); }
}
