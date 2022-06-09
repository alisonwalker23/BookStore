import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user:User=new User();
  constructor(private httpClient: HttpClient) { }

authenticateUser(user:User):Observable<any>{
  console.log(user )
  console.log(user.name, user.password + "sdsd")
  return this.httpClient.post<any>('http://localhost:9000/gateway/users/login',user);

}

isValid(token:any):Observable<any>{
  return this.httpClient.post<any>('http://localhost:9000/gateway/users/isAuthenticated',{},{
    headers:new HttpHeaders().set("Authorization", "Bearer " + token)
  })
}

setToken(token:string){
  localStorage.setItem("bearerToken", token);
}

getToken(){
  return localStorage.getItem("bearerToken")
}
setUserId(id:number){
  return sessionStorage.setItem("userId", id+"")
 }
getUserId(){
  return localStorage.getItem('userId');
}
}
