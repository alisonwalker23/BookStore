import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { Cart } from '../model/cart';
import { UserBook } from '../model/userBook';




import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserBookService {

  constructor(private httpClient:HttpClient, private authService:AuthService) { }

  token:any = this.authService.getToken();

getUserBooksById(userid:number, userBook:UserBook){
return this.httpClient.post<UserBook>("http://localhost:9000/user/" + userid + "/books", {
  headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
  
});

}
addUserBooksById(userBooks:UserBook):Observable<UserBook>{
  return this.httpClient.post<UserBook>("http://localhost:9000/userBooks/" + userBooks.userId + "/books", userBooks)

}
}
