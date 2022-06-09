import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Order } from '../model/order';
import { OrderService } from '../services/order.service';
import { UpdateOrderComponent } from '../update-order/update-order.component';
import {User} from "../model/user";
import {UserService} from "../services/user.service";
import {AuthService} from "../services/auth.service";



interface Status {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  statuses: Status[] = [
    {value: 'pending-0', viewValue: 'Pending'},
    {value: 'fulfilled-1', viewValue: 'Fulfilled'},
    {value: 'delivered-2', viewValue: 'Delivered'}
  ]
  user: User = new User();
  username = " ";
  orders : Order[] = [];
  order : Order = new Order();
  selectedOption='option2'
  updateSelectedResponse=''




  constructor(private orderService: OrderService, private dialog: MatDialog,private userService:UserService,private authService:AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{

        let userid = +response['userid'];

        console.log(userid);

        this.orderService.getAllOrdersForAUser(userid).subscribe({
          next:(orders)=>{
            console.log(orders);
            this.orders = orders
          }
        })
        this.userService.getUserById(userid).subscribe({
          next:(user)=>{
            console.log(user)
            this.user = user;
          }
        })

    this.orderService.getAllOrders().subscribe({
      next:(orders)=> {
        console.log(orders);
        this.orders = orders;
      }})
      }})

  }


  updateOrder(order:Order){
    console.log("updateOrder clicked")
    let orderCopy = Object.assign({},order );
    this.dialog.open(UpdateOrderComponent,{
      width:"250px",
      data:orderCopy
    }).afterClosed().subscribe(()=>{this.ngOnInit();
    })
  }



}
