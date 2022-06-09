import { Component, OnInit } from '@angular/core';
import { RoutingService } from 'app/services/routing.service';
import { Book } from '../model/book';
import { BookOrder } from '../model/bookOrder';
import { Cart } from '../model/cart';
import { User } from '../model/user';
import { UserBook } from '../model/userBook';
import { AuthService } from '../services/auth.service';
import { CartService } from '../services/cart.service';
import { UserBookService } from '../services/user-book.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  books :Book[] = [];
  userBook : UserBook = new UserBook();
  userBooks: UserBook[] =[];
  book:Book = new Book();
  user:User = new User();
  cart : Cart = new Cart();

  // carts : Cart [] = []
  bookOrders : BookOrder[] = [];

  totalCart: number = 0;
  bookTitle: string = "";

  carts : Cart [] = []



  constructor(private authService:AuthService, private userService:UserService, private cartService:CartService, private routing:RoutingService) { }

  ngOnInit() {
    let token = this.authService.getToken();


    this.authService.isValid(token).subscribe({next:(response)=> {

        let userid = response['userid'];
        console.log("002309")
        console.log(userid);
        this.cartService.getCartByUserId(userid).subscribe({
          next:(cart)=>{
            console.log(cart)
            this.cart = cart;
            this.bookOrders = cart.bookOrders;
            this.total();
          }
        })
      }
    })
   }



   delete(bookOrder:BookOrder){
    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{
      let userid = response['userid'];
      this.cart.id = userid;
      console.log(userid);

      this.cartService.getCartByUserId(userid).subscribe({
        next:(response)=>{
          console.log(response);
          let cartToBeUpdated = new Cart();
          let bookOrders = response.bookOrders
          Object.assign(cartToBeUpdated, response)


          cartToBeUpdated.bookOrders= bookOrders.filter((b)=>{
            return b.bookId != bookOrder.bookId;
          })
          this.cartService.updateCart(cartToBeUpdated).subscribe({
            next:(response)=>{
              console.log(response + " cart updated")
              this.total();
              history.go(0);
            }
          })
        }})
    }})

      }

      total(){
        if(this.totalCart <= 1){

          for (let i = 0; i < this.cart.bookOrders.length; i++) {
            this.totalCart += this.cart.bookOrders[i].price;
          }
        }
        }


        gotoCheckout(){
          this.routing.goToCheckout();
        }
}

