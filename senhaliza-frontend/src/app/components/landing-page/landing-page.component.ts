import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
  @Output() logearse = new EventEmitter<void>();
  @Output() registrarse = new EventEmitter<void>();
  
  irALogear(){
    this.logearse.emit();
  }
  irARegistrar(){
    this.registrarse.emit();  
  }
}
