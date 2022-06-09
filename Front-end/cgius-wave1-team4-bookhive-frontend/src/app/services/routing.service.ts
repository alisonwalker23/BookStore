import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  constructor(private router : Router) { }

goToSignUp(){
  this.router.navigate(['signup'])
}
goToHome(){
  this.router.navigate(['home'])
}
goToShop(){
  this.router.navigate(['shop'])
}
goToLogin(){
  this.router.navigate(['login'])
}
goToAdminView(){
  this.router.navigate(['adddeleteupdate'])
}
gotToAccount(){
  this.router.navigate(['account'])
}
gotToCart(){
  this.router.navigate(['cart'])
}
goToCheckout(){
  this.router.navigate(['checkout'])
}
goToUpdateOrderStatus(){
  this.router.navigate(['order'])
}
goToConfirmationPage(){
  this.router.navigate(['confirmationPage'])
}
}
