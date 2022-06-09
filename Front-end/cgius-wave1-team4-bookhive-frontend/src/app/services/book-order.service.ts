import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookOrder } from 'app/model/bookOrder';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BookOrderService {

  constructor(private httpClient: HttpClient, private authService: AuthService) { }

  token: any=this.authService.getToken();
  
  getBooksFromCart(cartId:number): Observable<BookOrder[]>{
    return this.httpClient.get<BookOrder[]>("http://localhost:9000/bookorders/cart/" + cartId, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
  }
}
