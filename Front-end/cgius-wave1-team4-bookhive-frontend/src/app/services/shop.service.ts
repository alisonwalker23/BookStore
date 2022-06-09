import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { BookOrder } from '../model/bookOrder';
import { Cart } from '../model/cart';
import { User } from '../model/user';

import { AuthService } from './auth.service';
import { UserBookService } from './user-book.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  cart: Cart = new Cart()
  user: User = new User();
  books : Book[] = []
  book : Book = new Book()
  bookOrder:BookOrder = new BookOrder();
  bookOrders:BookOrder[] = [];
  
  constructor(private httpClient:HttpClient, private authService:AuthService, private userService:UserService) { }
  
  token:any = this.authService.getToken();
  fetchBooks():Observable<Book[]>{
    return this.httpClient.get<Book[]>("http://localhost:9000/books",{
      headers: new HttpHeaders().set("Authorization", `Bearer ${this.token}`)
    });
  }
  addNewBookToCart(cart:Cart, bookOrder:BookOrder) : Observable<Cart>{
    
    return this.httpClient.post<Cart>("http://localhost:9000/cart/" + cart.id, cart, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
      
    });
    
  }

  createNewCart(cart: Cart) : Observable<Cart>{
    return this.httpClient.post<Cart>("http://localhost:9000/cart", cart,{
      headers: new HttpHeaders().set("Authorization", `Bearer ${this.token}`)
    });
  }
  // findFirstCartByUserId(userid:number , cart:Cart): Observable<Cart[]>{
  //   return this.httpClient.get<Cart[]>("http://localhost:9000/cart/user/" + userid, {
  //     headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
      
  //   });
  // }

}
