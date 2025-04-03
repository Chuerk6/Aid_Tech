import { Component, EventEmitter, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LevelService } from '../../../services/level.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Level } from '../../../models/level';
import { HttpErrorResponse } from '@angular/common/http';
import { ServerError } from '../../../models/server-error';

@Component({
  selector: 'app-level-new',
  templateUrl: './level-new.component.html',
  styleUrl: './level-new.component.css',
})
export class LevelNewComponent {
  @Input() idLevel!: number;
  @Output() register = new EventEmitter<void>();
  detalleForm!: FormGroup;
  noExiste: boolean = false;

  constructor(
    private levelService: LevelService,
    private formBuilder: FormBuilder,
    private enrutador: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.cargaLevel();
  }

  registrarNivel() {
    const level: Level = {
      id: parseInt(this.detalleForm.get('id')!.value),
      name: this.detalleForm.get('name')!.value,
      description: this.detalleForm.get('description')!.value
    };
    if (this.idLevel != 0) {
      this.levelService.actualizarNivel(level).subscribe({
        next: () => {
          this._snackBar.open('El nivel se actualizó', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-niveles']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.levelService.registrarNivel(level).subscribe({
        next: () => {
          this._snackBar.open('El nivel se registró', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-niveles']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    this.register.emit();
  }

  cargaLevel() {
    this.detalleForm = this.formBuilder.group({
      id: [''],
      name: ['',[Validators.required, Validators.maxLength(100), Validators.minLength(3)]],
      description: ['',[Validators.required, Validators.maxLength(500), Validators.minLength(1)]]
    });

    //this.idLevel = this.ruta.snapshot.params['codigo'];

    if (this.idLevel != 0 && this.idLevel != undefined) {
      this.levelService.buscarNivel(this.idLevel).subscribe({
        next: (data: Level) => {
          this.detalleForm.get('id')?.setValue(data.id);
          this.detalleForm.get('name')?.setValue(data.name);
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
      this.idLevel = 0;
      this.detalleForm.get('id')?.setValue(this.idLevel);
    }
  }

  cancelar(){ this.register.emit(); }
}
