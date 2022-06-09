import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private httpClient:HttpClient) { }

  addNewUser(users:User):Observable<User>{
    return this.httpClient.post<User>("http://localhost:9000/gateway/users/register", users);
  }


}
