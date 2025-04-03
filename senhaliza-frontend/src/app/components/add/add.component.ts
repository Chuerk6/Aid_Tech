import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrl: './add.component.css'
})
export class AddComponent {
  constructor(public dialogRef: MatDialogRef<AddComponent>,@Inject(MAT_DIALOG_DATA) public data:any){}
  
  id: number = this.data.id;
  option: string = this.data.option;

  cancelar(){ this.dialogRef.close(false); }
  confirmar(){ this.dialogRef.close(true); }
}
