import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Student } from '../../../models/student';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { StudentService } from '../../../services/student.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent {
  displayedColumns: string[] = ['id','name','email','phone','avatar','level','user','acciones'];
  dsList = new MatTableDataSource<Student>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private studentService: StudentService, private snackBar: MatSnackBar) {}

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

  cargarLista(){
    this.studentService.listarEstudiantes().subscribe({
      next:(data: Student[])=>{
        this.dsList = new MatTableDataSource(data);
        this.dsList.paginator = this.paginator;
      },
      error: (err)=>{ console.log(err);}
    })
  }

  eliminarEstudiante(id: number){
    this.studentService.eliminarEstudiante(id).subscribe({
      next:()=>{
        this.cargarLista();
      },
      error:(err:HttpErrorResponse)=>{
        if(err.error.status==500){
          this.snackBar.open("Existen entidades que dependen del Estudiante", "Ok");
        }
      }
    })
  }

}


