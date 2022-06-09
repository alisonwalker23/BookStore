import { Component, Inject, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { RoutingService } from '../services/routing.service';
import { Order } from '../model/order';
import { User } from '../model/user';
import { AuthService } from '../services/auth.service';
import {OrderService } from '../services/order.service';
import { UserService } from '../services/user.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ViewOrderComponent } from '../view-order/view-order.component';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  books:Book[] = []
  orders: Order[] = [];
  user: User = new User();
  users: User[] = [];
  username = " ";
  order: Order = new Order();

  constructor(private orderService: OrderService, private authService:AuthService,private userService:UserService, private dialog: MatDialog) { }

  ngOnInit(){
    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{

      let userid = +response['userid'];

      console.log(userid);

    this.orderService.getAllOrdersForAUser(userid).subscribe({
      next:(orders)=>{
        console.log(orders);
        this.orders = orders;
      }
    })
    this.userService.getUserById(userid).subscribe({
      next:(user)=>{
        console.log(user)
        this.user = user;
      }
    })
    

    }})

  }

  viewOrder(order:Order) {
    let orderCopy = Object.assign({}, order);
    this.dialog.open(ViewOrderComponent, {
      width: "300px",
      data: orderCopy
    })
  }

  

}
