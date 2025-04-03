import { animate, keyframes, style, transition, trigger } from '@angular/animations';
import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { navbarDataAdmin } from './nav-data-admin';
import { navbarDataStudent } from './nav-data-student';
import { UserService } from '../../services/user.service';
import { Route, Router } from '@angular/router';

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrl: './sidenav.component.css',
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({opacity: 0}),
        animate('350ms',
          style({opacity: 1})
        )
      ]),
      transition(':leave', [
        style({opacity: 1}),
        animate('350ms',
          style({opacity: 0})
        )
      ])
    ]),
    trigger('rotate', [
      transition(':enter', [
        animate('1000ms', 
          keyframes([
            style({transform: 'rotate(0deg)', offset: '0'}),
            style({transform: 'rotate(2turn)', offset: '1'})
          ])
        )
      ])
    ])
  ]
})
export class SidenavComponent implements OnInit {
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  @Output() usuarioDeslogeado = new EventEmitter<void>();
  collapsed = false;
  screenWidth = 0;
  navData: any[] = [];

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    if(typeof window !== 'undefined'){
    this.screenWidth = window.innerWidth;
    }
    if(this.screenWidth <= 768 ) {
      this.collapsed = false;
      this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
    }
  }

  constructor(private userService: UserService, private router: Router){}

  ngOnInit(): void {
    if(typeof window !== 'undefined'){
      this.screenWidth = window.innerWidth;
      this.toggleCollapse();
    }
    if(typeof localStorage !== 'undefined'){
      if(localStorage.getItem("authorities") === "ROLE_STUDENT"){
        this.navData = navbarDataStudent;
      }
      if(localStorage.getItem("authorities") === "ROLE_ADMIN"){
        this.navData = navbarDataAdmin;
      }
    }
  }

  toggleCollapse(): void {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }

  logout(opcion: string){
    if(opcion === "Cerrar sesion"){
      this.userService.deslogearUsuario();
      this.usuarioDeslogeado.emit();
    }
  }
}
