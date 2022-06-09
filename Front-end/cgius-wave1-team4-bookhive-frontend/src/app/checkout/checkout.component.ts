import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { User } from '../model/user';
import { ShopService } from '../services/shop.service';
import {Order} from "../model/order";
import {Cart} from "../model/cart";
import {BookOrder} from "../model/bookOrder";
import {OrderService} from "../services/order.service";
import {UserService} from "../services/user.service";
import {AuthService} from "../services/auth.service";
import { CartService } from '../services/cart.service';
import {UserBook} from "../model/userBook";
import { Observable } from 'rxjs';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  user: User = new User();
  books: Book[] = [];
  book: Book = new Book();
  order: Order = new Order();
  cart: Cart = new Cart();
  carts : Cart[] = [];
  bookOrder: BookOrder = new BookOrder();
  bookOrders : BookOrder[] = [];
  userBooks: UserBook[] =[];
  orders : Order [] = [];
  showCheckoutMessage = false;
  orderUserId :number = 0;
  totalCart: number = 0;
  userOrderId:number=0;


  constructor(private shopService: ShopService, private orderService: OrderService, private userService: UserService, private authService: AuthService, private cartService:CartService, private routingService:RoutingService ) {
  }


  ngOnInit() {
    let token = this.authService.getToken();
    this.authService.isValid(token).subscribe({next:(response)=>{

        let userid = response['userid'];

        console.log(userid);
      this.orderUserId = userid;
        this.cartService.getCartByUserId(userid).subscribe({
          next:(response)=>{
            console.log(response)
            this.cart = response;
            this.bookOrders = response.bookOrders;
            this.total();
          }})


      }})


  }


  createOrder(id:number) {

   let order: Order = new Order();

   order.orderId= this.orderService.generateOrderId()
   order.userId = this.orderUserId;
   console.log(this.orderUserId)
   order.status="pending";
   order.cart= this.cart;
   
    console.log(order);
    



    this.orderService.createOrder(order).subscribe({
      next: (response) => {
        console.log(response);
        console.log("order created");
        
        
        console.log(response.orderId)
        this.userOrderId = response.orderId;
        console.log(this.userOrderId + " fgh")
        this.showCheckoutMessage = true;
      }
    });

    this.cartService.deleteCartByCartId(id).subscribe({
      next:(message)=>{
        this.carts = this.carts.filter(cart => cart.id != cart.id)
      }
    })
  }

  total(){
    if(this.totalCart <= 1){

      for (let i = 0; i < this.cart.bookOrders.length; i++) {
        this.totalCart += this.cart.bookOrders[i].price;
      }
    }
    }
  //future enhancement delete cart after checkout
  // as well as a view for the customer with order status and tracking number ect.

  // checkoutMessage(){
  //   this.showCheckoutMessage = true;
  // }

}

