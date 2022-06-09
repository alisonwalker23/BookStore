import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient, private authService:AuthService) { }

  token:any=this.authService.getToken();
//works in postman
getAllBooks(){
 return this.httpClient.get<Book[]>("http://localhost:9000/books", {
  headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
})
}
//works in postman
addNewBook(book : Book): Observable<Book> {
  return this.httpClient.post<Book>("http://localhost:9000/books", book, {
    headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
  });
}
//works in postman
getBooksById(book:Book){
  return this.httpClient.get<Book>("http://localhost:9000/books/"+ book.id);
}
//works in postman
updateBook(book:Book){
  return this.httpClient.put<Book>("http://localhost:9000/books/" + book.id, book, {
    headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
  });

}
//works in postman
deleteBook(id:number): Observable<any>{
  return this.httpClient.delete("http://localhost:9000/books/" + id, {
    headers: new HttpHeaders().set('Authorization', `Bearer ${this.token}`)
  });
}
//getBooksByCategory does not work yet returns a 404
// getBooksByCategory(book : Book){
//   return this.httpClient.get<Book>("http://localhost:3000/books/category" + book.category);
// }


// getBookByAvailability(book: Book ){
//   return this.httpClient.get<Book>("http://localhost:3000/books/availability" + book.availability);
// }
}
