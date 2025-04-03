import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmComponent } from '../../confirm/confirm.component';
import { Plan } from '../../../models/plan';
import { PlanService } from '../../../services/plan.service';

@Component({
  selector: 'app-plan-list',
  templateUrl: './plan-list.component.html',
  styleUrl: './plan-list.component.css'
})
export class PlanListComponent {
  displayedColumns: string[] = ['id', 'name', 'description', 'price', 'time','acciones'];
  dsList = new MatTableDataSource<Plan>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private planService: PlanService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
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
    this.planService.listarPlanes().subscribe({
      next: (data: Plan[]) => {
        this.dsList = new MatTableDataSource(data);
        this.dsList.paginator = this.paginator;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  eliminarPlan(id: number) {
    let confimarEliminacion = this.dialog.open(ConfirmComponent);
    confimarEliminacion.afterClosed().subscribe((result) => {
      if (result) {
        this.planService.eliminarPlan(id).subscribe({
          next: () => { this.cargarLista(); },
          error: (err: HttpErrorResponse) => { 
            if (err.error.status == 500) { 
              this.snackBar.open('Existen entidades que dependen del Plan','Ok'); 
            }
          },
        });
      }
    });
  }
}

