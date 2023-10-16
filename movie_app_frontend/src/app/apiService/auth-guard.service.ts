import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthapiService } from './authapi.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authapiService:AuthapiService,private route:Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    const allowedRoles = route.data['allowedRoles'] as string[];
    if(!this.authapiService.isUerLoggedIn()){//not logged in
      return this.route.parseUrl('/login');
    }
    if (this.authapiService.hasAnyRole(allowedRoles)) {
      return true;
    } else {
      return this.route.parseUrl('/unauthorized');
    }
  }
}
