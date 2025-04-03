import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmComponent } from '../../confirm/confirm.component';
import { Authority } from '../../../models/authority';
import { AuthorityService } from '../../../services/authority.service';
import { AddComponent } from '../../add/add.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-authority-list',
  templateUrl: './authority-list.component.html',
  styleUrl: './authority-list.component.css'
})
export class AuthorityListComponent {
  displayedColumns: string[] = ['id', 'name', 'description', 'acciones'];
  
  dsList = new MatTableDataSource<Authority>();
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private authorityService: AuthorityService,
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
    this.authorityService.listarAutoridades().subscribe({
      next: (data: Authority[]) => {
        this.dsList = new MatTableDataSource(data);
        this.dsList.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  eliminarAutoridad(id: number) {
    let confimarEliminacion = this.dialog.open(ConfirmComponent);
    confimarEliminacion.afterClosed().subscribe((result) => {
      if (result) {
        this.authorityService.eliminarAutoridad(id).subscribe({
          next: () => { this.cargarLista(); },
          error: (err: HttpErrorResponse) => { 
            if (err.error.status == 500) { 
              this.snackBar.open('Existen entidades que dependen del Autoridad','Ok'); 
            }
          },
        });
      }
    });
  }

  agregarAutoridad(){
    this.dialog.open(AddComponent, {data: {id: 0, option: "autoridad"}});
  }
    
  actualizarAutoridad(id: string){
    const dialogRef = this.dialog.open(AddComponent, {data: { id: id, option: "autoridad"}});
      dialogRef.afterClosed().subscribe(result =>{ if(!result) this.enrutador.navigate(['/lista-autoridades'])})  
  };
}
