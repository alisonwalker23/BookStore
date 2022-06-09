import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';

import { map, Observable, tap } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CanactivateGuard implements CanActivate {

  constructor(private authService: AuthService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> {
      console.log("canActivate is called")
    let token = this.authService.getToken();
    console.log(token);
    return this.authService.isValid(token).pipe(tap((response:any)=>{
      console.log(response["userid"])
      this.authService.setUserId(response["userid"])
    }),map((response)=>{
      
      return response['isAuthenticated']
    }))
  }
  
}
