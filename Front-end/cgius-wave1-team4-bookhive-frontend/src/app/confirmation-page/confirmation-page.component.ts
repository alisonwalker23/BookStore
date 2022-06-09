import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { AuthService } from '../services/auth.service';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-confirmation-page',
  templateUrl: './confirmation-page.component.html',
  styleUrls: ['./confirmation-page.component.css']
})
export class ConfirmationPageComponent implements OnInit {
 
  order:Order=new Order();
  
  constructor(private orderService:OrderService, private authService:AuthService) { }

  ngOnInit(){

    let token = this.authService.getToken();
    this.authService.isValid(token).subscribe({next:(response)=>{

        let userid = response['userid'];

        console.log(userid);

      }})



    let order = new Order();
    order.orderId= this.orderService.generateOrderId()
    order.status="pending";
  }

}
