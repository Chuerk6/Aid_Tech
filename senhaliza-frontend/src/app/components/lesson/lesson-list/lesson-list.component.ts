import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmComponent } from '../../confirm/confirm.component';
import { Lesson } from '../../../models/lesson';
import { LessonService } from '../../../services/lesson.service';
import { AddComponent } from '../../add/add.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lesson-list',
  templateUrl: './lesson-list.component.html',
  styleUrl: './lesson-list.component.css'
})
export class LessonListComponent {
  displayedColumns: string[] = ['id', 'theme', 'description', 'acciones'];
  dsList = new MatTableDataSource<Lesson>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private lessonService: LessonService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private enrutador: Router
  ) {}

  ngOnInit() {
    this.cargarLista();
    this.dsList.paginator = this.paginator;
    this.dsList.sort = this.sort;
  }

  ngAfterViewInit() {
    this.dsList.paginator = this.paginator;
    this.dsList.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dsList.filter = filterValue.trim().toLowerCase();

    if (this.dsList.paginator) {
      this.dsList.paginator.firstPage();
    }
  }

  cargarLista() {
    this.lessonService.listarLecciones().subscribe({
      next: (data: Lesson[]) => {
        this.dsList = new MatTableDataSource(data);
        this.dsList.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  eliminarLeccion(id: number) {
    let confimarEliminacion = this.dialog.open(ConfirmComponent);
    confimarEliminacion.afterClosed().subscribe((result) => {
      if (result) {
        this.lessonService.eliminarLeccion(id).subscribe({
          next: () => { this.cargarLista(); },
          error: (err: HttpErrorResponse) => { 
            if (err.error.status == 500) { 
              this.snackBar.open('Existen entidades que dependen de la Leccion','Ok'); 
            }
          },
        });
      }
    });
  }

  agregarLeccion(){
    this.dialog.open(AddComponent, {data: {id: 0, option: "leccion"}});
  }
    
  actualizarLeccion(id: string){
    const dialogRef = this.dialog.open(AddComponent, {data: { id: id, option: "leccion"}});
    dialogRef.afterClosed().subscribe(result =>{ if(!result) this.enrutador.navigate(['/lista-lecciones'])})  
    };
}

