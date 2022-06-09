import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Book } from '../model/book';
import { UpdateComponent } from '../update/update.component';
import { RoutingService } from '../services/routing.service';

@Component({
  selector: 'app-adddeleteupdate',
  templateUrl: './adddeleteupdate.component.html',
  styleUrls: ['./adddeleteupdate.component.css']
})
export class AdddeleteupdateComponent implements OnInit {

  books: Book[] = [];
  book: Book = new Book();


  errorMessage : string="";
  constructor(private productService:ProductService, private dialog: MatDialog, private routingService: RoutingService) { }

  ngOnInit(){
    this.productService.getAllBooks().subscribe({
      next:(books)=>{
        console.log(books);
        this.books = books;
      }
    })
  }
  add(){
    console.log("add button clicked")
    if(this.book.id != 0 && this.book.title != "" && this.book.price != 0 && this.book.category != ""){
      this.productService.addNewBook(this.book).subscribe({
        next:(book)=>{
          console.log("book")
          this.books.push(book)
          this.errorMessage=""
        },
        error:(errorResponse)=>{
          this.errorMessage = errorResponse.error;
          if (this.errorMessage === "[object Object]") {
            this.errorMessage = "Enter valid information";
          }
        }
      })
    }else{
      this.errorMessage = "Fields cannot be empty or zero"
    }
    this.book.id=0;
    this.book.title="";
    this.book.price=0;
    this.book.category="";
    this.book.authorName="";
    this.book.quantity=0;
    this.book.description="";
    this.book.image="";

  }
  //delete only work's with line 54 being error otherwise it throws a 404 error but still deletes the book from the data base and when you refresh the page it deleted...If you change next to error it works fine
  delete(id:number){
    console.log("delete clicked")
    this.productService.deleteBook(id).subscribe({
      error:(message)=>{
        this.books = this.books.filter(book => book.id != id);
      },
    })
  }
  update(book:Book){
    console.log("update clicked")
    let bookCopy = Object.assign({}, book);
    this.dialog.open(UpdateComponent,{
      width:"250px",
      data:bookCopy
    }).afterClosed().subscribe(()=>{this.ngOnInit();
  })
  }
  public files: any[] | undefined;

  contructor() { this.files = []; }

  onFileChanged(event: any) {
    this.files = event.target.files;
  }

  updateOrder(){
    this.routingService.goToUpdateOrderStatus();
  }

}
