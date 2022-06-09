import { Component, OnInit } from '@angular/core';
import { RoutingService } from '../services/routing.service';
import { Book } from '../model/book';
import { Cart } from '../model/cart';
import { User } from '../model/user';
import { UserBook } from '../model/userBook';
import { AuthService } from '../services/auth.service';
import { ShopService } from '../services/shop.service';
import { UserService } from '../services/user.service';
import { BookOrder } from '../model/bookOrder';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  books: Book[] = [];
  book: Book = new Book();
  user: User = new User();
  userBooks: UserBook[] = [];
  userBook: UserBook = new UserBook();
  carts: Cart[] = [];
  cart: Cart = new Cart();
  bookOrder : BookOrder = new BookOrder();
  bookOrders : BookOrder[] =[];

  constructor(private shopService:ShopService, private authService: AuthService, private userService: UserService, private routing:RoutingService) { }

  ngOnInit(): void {
    this.shopService.fetchBooks().subscribe({
      next:(books)=>{
        if(this.book.quantity < 3) {
        console.log(books);
        this.books = books;
      }
      }
    })
  }

  addToCard(){
    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{

      let userid = response['userid'];

      console.log(userid);

    this.shopService.addNewBookToCart(this.cart, this.bookOrder).subscribe({
      next:(bookOrder)=>{
        
        console.log(bookOrder + "sdsd");
        this.bookOrders.push(this.bookOrder);
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

gotoShop(){
this.routing.goToShop();
}

}
