import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ImageService } from '../../../services/image.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Image } from '../../../models/image';
import { ServerError } from '../../../models/server-error';

@Component({
  selector: 'app-image-new',
  templateUrl: './image-new.component.html',
  styleUrl: './image-new.component.css'
})
export class ImageNewComponent {
  @Input() idImage!: number;
  @Output() register = new EventEmitter<void>();
  detalleForm!: FormGroup;
  noExiste: boolean = false;

  constructor(
    private imageService: ImageService,
    private formBuilder: FormBuilder,
    private enrutador: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.cargarImagen();
  }

  registrarImagen() {
    const image: Image = {
      id: parseInt(this.detalleForm.get('id')!.value),
      link: this.detalleForm.get('link')!.value,
      meaning: this.detalleForm.get('meaning')!.value
    };
    if (this.idImage != 0) {
      this.imageService.actualizarImagen(image).subscribe({
        next: () => {
          this._snackBar.open('La imagen se actualizó', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-imagenes']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.imageService.registrarImagen(image).subscribe({
        next: () => {
          this._snackBar.open('La imagen se registró', 'Ok', {
            duration: 1000,
          });
          this.enrutador.navigate(['/lista-imagenes']);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    this.register.emit();
  }

  cargarImagen() {
    this.detalleForm = this.formBuilder.group({
      id: [''],
      link: [''],
      meaning: ['']
    });

    //this.idImage = this.ruta.snapshot.params['codigo'];

    if (this.idImage != 0 && this.idImage != undefined) {
      this.imageService.buscarImagen(this.idImage).subscribe({
        next: (data: Image) => {
          this.detalleForm.get('id')?.setValue(data.id);
          this.detalleForm.get('link')?.setValue(data.link);
          this.detalleForm.get('meaning')?.setValue(data.meaning);
        },
        error: (err: ServerError) => {
          if (err.status == 404) {
            this.noExiste = true;
          }
          console.log(err);
        },
      });
    } else {
      this.idImage = 0;
      this.detalleForm.get('id')?.setValue(this.idImage);
    }
  }

  cancelar(){ this.register.emit(); }
}
