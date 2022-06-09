import { Component, OnInit } from '@angular/core';
import { AccountComponent } from '../account/account.component';
import { Book } from '../model/book';
import { AuthService } from '../services/auth.service';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private routingService:RoutingService, private authService:AuthService) { }

  ngOnInit(): void {
  }
logout(){
  localStorage.removeItem("bearerToken")
  console.log("removeItem")
  localStorage.clear();
  console.log("clear")
  this.routingService.goToHome();
}
signUp(){
  this.routingService.goToSignUp();
}
gotoShop(){
  this.routingService.goToShop();
}
login(){
  this.routingService.goToLogin();
}
userAccount(){
  this.routingService.gotToAccount();
}
adminView(){
  this.routingService.goToAdminView()
}
cart(){
  this.routingService.gotToCart();
}
home(){
  this.routingService.goToHome();
}

}
