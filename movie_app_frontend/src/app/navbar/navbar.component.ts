import { Component } from '@angular/core';
import { AuthapiService } from '../apiService/authapi.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isAdmin = false;
  username:any;

  constructor(private authapiService:AuthapiService,private router:Router){}

  ngOnInit(){
    this.isAdmin = this.authapiService.isRoleAdmin();
    this.username = this.authapiService.getUsername();
  }

  logout(){
    this.authapiService.logoutUser();
    this.router.navigate(['/login']);
  }



}
