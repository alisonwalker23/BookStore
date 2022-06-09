import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
//import { userInfo } from 'os';
import { Observable } from 'rxjs';
import { Order } from '../model/order';
import { User } from '../model/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient:HttpClient, private authService:AuthService) { }

  token: any=this.authService.getToken();
  //works in postman
  getAllOrders():Observable<Order[]>{
    return this.httpClient.get<Order[]>("http://localhost:9000/orders", {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
  }

  createOrder(Order:Order):Observable<Order>{
    return this.httpClient.post<Order>("http://localhost:9000/orders", Order, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
  }

  //get all order for a specific user
  getAllOrdersForAUser(userId:number): Observable<Order[]>{
    return this.httpClient.get<Order[]>("http://localhost:9000/users/" + userId + "/orders", {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }

   //get a specific order of a specific user
   //works in postman
   getOrderByOrderId(orderId:number){
    return this.httpClient.get<Order>("http://localhost:9000/orders/" + orderId, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }

   //update a order
   //works in postman with order.id...not sure how to use user.id yet
   updateAnOrder(order:Order){
     return this.httpClient.put<Order>("http://localhost:9000/orders/" + order.orderId, order, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }
   //add a book to order list
   //works in postman
   addBookToOrder(order:Order): Observable<Order>{
    return this.httpClient.post<Order>("http://localhost:9000/orders", order, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }
   //delete a order from order list
   //works in postman
   deleteBookFromOrder(orderId:number):Observable<any>{
    return this.httpClient.delete("http://localhost:9000/orders/" + orderId, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }

   updateOrderStatus(orderId:number,status:string):Observable<any>{
    return this.httpClient.put("http://localhost:9000/orders/" + orderId + "/" + status, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
    })
   }


  generateOrderId() {
    return Math.floor(Math.random() * 1000000);
  }


}
