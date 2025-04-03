import { Component, EventEmitter, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServerError } from '../../../models/server-error';
import { LessonService } from '../../../services/lesson.service';
import { Lesson } from '../../../models/lesson';

@Component({
  selector: 'app-lesson-new',
  templateUrl: './lesson-new.component.html',
  styleUrl: './lesson-new.component.css'
})
export class LessonNewComponent {
  @Input() idLesson!: number;
  @Output() register = new EventEmitter<void>();
  detalleForm!: FormGroup;
  noExiste: boolean = false;

  constructor(
    private lessonService: LessonService,
    private formBuilder: FormBuilder,
    private enrutador: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.cargaLesson();
  }

  registrarLeccion() {
    const lesson: Lesson = {
      id: parseInt(this.detalleForm.get('id')!.value),
      theme: this.detalleForm.get('theme')!.value,
      description: this.detalleForm.get('description')!.value
    };
    if (this.idLesson != 0) {
      this.lessonService.actualizarLeccion(lesson).subscribe({
        next: () => {
          this._snackBar.open('La leccion se actualizó', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-lecciones']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.lessonService.registrarLeccion(lesson).subscribe({
        next: () => {
          this._snackBar.open('La leccion se registró', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-lecciones']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    this.register.emit();
  }

  cargaLesson() {
    this.detalleForm = this.formBuilder.group({
      id: [''],
      theme: ['',[Validators.required, Validators.maxLength(100), Validators.minLength(3)]],
      description: ['',[Validators.required, Validators.maxLength(500), Validators.minLength(1)]]
    });

    if (this.idLesson != 0 && this.idLesson != undefined) {
      this.lessonService.buscarLeccion(this.idLesson).subscribe({
        next: (data: Lesson) => {
          this.detalleForm.get('id')?.setValue(data.id);
          this.detalleForm.get('theme')?.setValue(data.theme);
          this.detalleForm.get('description')?.setValue(data.description);
        },
        error: (err: ServerError) => {
          if (err.status == 404) {
            this.noExiste = true;
          }
          console.log(err);
        },
      });
    } else {
      this.idLesson = 0;
      this.detalleForm.get('id')?.setValue(this.idLesson);
    }
  }

  cancelar(){ this.register.emit(); }
}
