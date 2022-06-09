import { Component, ErrorHandler, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { ProductService } from '../services/product.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { UpdateComponent } from '../update/update.component';
import { ShopService } from '../services/shop.service';
import { Cart } from '../model/cart';
import { User } from '../model/user';
import { UserBook } from '../model/userBook';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { BookOrder } from '../model/bookOrder';
import { CartService } from '../services/cart.service';
import { Order } from '../model/order';
import { BookDetailsComponent } from '../book-details/book-details.component';


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  books : Book[] = [];
  user : User = new User();
  book : Book = new Book();
  cart: Cart = new Cart();
  carts: Cart[] = [];
  userBooks: UserBook[] =[];
  usersBook : UserBook = new UserBook();
  bookOrder : BookOrder = new BookOrder();
  bookOrders : BookOrder[] =[];
  bookGenres : string[] = [];

  searchTerm: string = "";


  errorMessage: string= ""
  constructor(private shopService:ShopService, private authService:AuthService, private userService:UserService, private dialog:MatDialog, private cartService:CartService) { }

  ngOnInit(){

    let token = this.authService.getToken();
    console.log(token);
    this.authService.isValid(token).subscribe({next:(response)=>{
        console.log(response);
      let userid = response['userid'];

      console.log(userid);

    this.shopService.fetchBooks().subscribe({
      next:(books)=>{
        console.log(books);
        this.books = books
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
  search(value: string): void{
    this.books = this.books.filter((val) => val.category.toLowerCase().includes(value));
  }

  addToCard(newBook:Book){

    let token = this.authService.getToken();

    this.authService.isValid(token).subscribe({next:(response)=>{

      let userid = response['userid'];
      this.cart.id = userid;
      console.log(userid);

      this.cartService.getCartByUserId(userid).subscribe({
        next:(response)=>{
          console.log(response);
          let cartToBeUpdated = new Cart();
          let bookOrder = new BookOrder();
          Object.assign(cartToBeUpdated, response)
          bookOrder.bookId = newBook.id;
          bookOrder.authorName = newBook.authorName
          bookOrder.category = newBook.category
          bookOrder.description = newBook.description
          bookOrder.image = newBook.image
          bookOrder.price = newBook.price
          bookOrder.quantity = newBook.quantity
          bookOrder.title = newBook.title

          cartToBeUpdated.bookOrders.push(bookOrder)
          this.cartService.updateCart(cartToBeUpdated).subscribe({
            next:(response)=>{
              console.log(response + " cart updated")

            }

          })
        }
      })
    // this.shopService.createNewCart(this.cart).subscribe({
    //   next:(cart)=>{
    //     console.log(cart+" asd")
    //     this.carts.push(cart)
    //   }
    // })
    this.userService.getUserById(userid).subscribe({
      next:(user)=>{
        // console.log(user)
        this.user = user;
      }
    })

    }})


}

openBook(book:Book){
  let bookCopy = Object.assign({}, book);
  this.dialog.open(BookDetailsComponent,{
    width:"800px",
    data:bookCopy
  })
}

selectedBrand: any;
isSelected(category:string) {
  console.log(category);
  this.selectedBrand = category;
}

populateGenres() {
  var books = this.books;
  
  for(let i = 0; i < books.length; i++){
    if(!this.bookGenres.includes(books[i].category)){
      this.bookGenres.push(books[i].category);
    }
  }
  this.bookGenres.sort(([a, b], [c, d]) => a.localeCompare(c) || b.localeCompare(d));
  
}


}
