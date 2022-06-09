import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../model/cart';


import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  carts : Cart[] =[]
  cart : Cart = new Cart();

  constructor(private httpClient:HttpClient , private authService:AuthService) { }
token:any = this.authService.getToken();

getCartByUserId(userid:number): Observable<Cart>{
  return this.httpClient.get<Cart>("http://localhost:9000/cart/user/" + userid, {
    headers : new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
});
}

deleteCartByCartId(id:number):Observable<any>{
  return this.httpClient.delete("http://localhost:9000/cart/" + id, {
    headers : new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
});
}

updateCart(cart:Cart):Observable<Cart[]>{
  return this.httpClient.put<Cart[]>("http://localhost:9000/cart/" + cart.id, cart, {
    headers : new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
});

}

}
