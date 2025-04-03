import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmComponent } from '../../confirm/confirm.component';
import { Exercise } from '../../../models/exercise';
import { ExerciseService } from '../../../services/exercise.service';
import { AddComponent } from '../../add/add.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-list.component.html',
  styleUrl: './exercise-list.component.css'
})
export class ExerciseListComponent {
  displayedColumns: string[] = ['id', 'level', 'typeQuestion','question','comment','lesson', 'acciones'];
  dsList = new MatTableDataSource<Exercise>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private exerciseService: ExerciseService,
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
    this.exerciseService.listarEjercicios().subscribe({
      next: (data: Exercise[]) => {
        this.dsList = new MatTableDataSource(data);
        this.dsList.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  eliminarEjercicio(id: number) {
    let confimarEliminacion = this.dialog.open(ConfirmComponent);
    confimarEliminacion.afterClosed().subscribe((result) => {
      if (result) {
        this.exerciseService.eliminarEjercicio(id).subscribe({
          next: () => { this.cargarLista(); },
          error: (err: HttpErrorResponse) => { 
            if (err.error.status == 500) { 
              this.snackBar.open('Existen entidades que dependen del Ejercicio','Ok'); 
            }
          },
        });
      }
    });
  }

  agregarEjercicio(){
    const dialogRef = this.dialog.open(AddComponent, {data: {id: 0, option: "ejercicio"}});    
    dialogRef.afterClosed().subscribe(result => { if(!result) this.enrutador.navigate(['lista-ejercicios'])});
  }
    
  actualizarEjercicio(id: string){
    const dialogRef = this.dialog.open(AddComponent, {data: { id: id, option: "ejercicio"}});
      dialogRef.afterClosed().subscribe(result =>{ if(!result) this.enrutador.navigate(['/lista-ejercicios'])});
  };
}
