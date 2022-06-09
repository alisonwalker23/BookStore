import { Component, Inject, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { BookOrder } from '../model/bookOrder';
import { Cart } from '../model/cart';
import { Order } from '../model/order';
import { User } from '../model/user';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';
import { UserService } from '../services/user.service';
import { OrderService } from '../services/order.service';
import { BookOrderService } from '../services/book-order.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BookDetailsComponent } from 'app/book-details/book-details.component';

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {

  bookOrder: BookOrder = new BookOrder();
  bookOrders: BookOrder[] = [];
  books: Book[] = [];
  orders: Order[] = [];
  user: User = new User();
  users: User[] = [];
  username = " ";
  cart: Cart = new Cart();
  order: Order = this.data;
  totalCart: number = 0;
  fulfilled = false;
  notFulfilled = false;

  constructor(@Inject(MAT_DIALOG_DATA)private data:any, private orderService: OrderService, private authService:AuthService,private userService:UserService,
   private cartService: CartService, private bookOrderService: BookOrderService, private dialogRef:MatDialog) { }


  ngOnInit(){
    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{

      let userid = +response['userid'];
      this.cart.id = userid;

      console.log(userid);

    this.orderService.getAllOrdersForAUser(userid).subscribe({
      next:(orders)=>{
        console.log(orders)
        this.orders = orders;
        this.cart = this.order.cart;
        this.bookOrders = this.cart.bookOrders;
        this.total();
        this.fullfilled();
        
      }
    })
    this.cartService.getCartByUserId(userid).subscribe({
      next:(cart)=>{
      console.log(this.cart);
        this.cart = this.cart;
        this.bookOrders = cart.bookOrders;
      }
    })
    this.userService.getUserById(userid).subscribe({
      next:(user)=>{
        console.log(user)
        this.user = user;
      }
    })
    // this.bookOrderService.getBooksFromCart(this.cart.id).subscribe({
    //   next:(bookOrders)=>{
    //     console.log(bookOrders)
    //     this.bookOrders = bookOrders;
    //   }
    // })
  }})

    }

    total(){
      if(this.totalCart <= 1){
        
        for (let i = 0; i < this.cart.bookOrders.length; i++) {
          this.totalCart += this.cart.bookOrders[i].price;
          console.log(this.totalCart.toFixed(2));
        }
      }
      }

      openBook(bookOrder:BookOrder){
        let bookCopy = Object.assign({}, bookOrder);
        this.dialogRef.open(BookDetailsComponent,{
          width:"800px",
          data:bookCopy
        })
      }

      fullfilled(){
        if (this.order.status.includes("shipped") || this.order.status.includes("Shipped") || this.order.status.includes("confirmed") || this.order.status.includes("Confirmed")|| this.order.status.includes("Complete") || this.order.status.includes("complete") || this.order.status.includes("delivered") || this.order.status.includes("Delivered")) {
          this.fulfilled = true;
        } else {
          this.notFulfilled = true;
        }
      }
  }

