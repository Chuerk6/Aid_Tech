import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';


@NgModule({

 declarations: [],

 imports: [

  CommonModule,
  MatToolbarModule,
  MatButtonModule,
  MatCardModule,
  MatTableModule,
  MatFormFieldModule,
  MatIconModule,
  MatSnackBarModule,
  MatPaginatorModule,
  MatInputModule,
  MatCheckboxModule,
  MatDialogModule,
  MatOptionModule,
  MatCheckboxModule,
  MatSelectModule

 ],

 exports: [

  MatToolbarModule,
  MatButtonModule,
  MatCardModule,
  MatTableModule,
  MatFormFieldModule,
  MatIconModule,
  MatSnackBarModule,
  MatPaginatorModule,
  MatInputModule,
  MatCheckboxModule,
  MatDialogModule,
  MatOptionModule,
  MatCheckboxModule,
  MatSelectModule

 ]

})

export class MaterialModule { }
