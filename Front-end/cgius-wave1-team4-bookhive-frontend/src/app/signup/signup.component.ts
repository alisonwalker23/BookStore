import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { RoutingService } from '../services/routing.service';
import { SignupService } from '../services/signup.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  users: User[] = [];
  user : User = new User();

  constructor(private signupService:SignupService, private router: RoutingService) { }

  ngOnInit(): void {
  }

  addUser(){
    this.signupService.addNewUser(this.user).subscribe({
      next:(addUser)=>{
        this.users.push(addUser)
        console.log(addUser)
        this.router.goToLogin();
      }
    })
  }

  gotoLogin(){
    this.router.goToLogin();
  }

}
