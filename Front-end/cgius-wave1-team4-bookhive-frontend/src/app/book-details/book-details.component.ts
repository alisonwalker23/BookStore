import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Book } from 'app/model/book';
import { RoutingService } from 'app/services/routing.service';
import { ShopComponent } from 'app/shop/shop.component';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {
  book : Book = this.data;
  books : Book[] = [];

  constructor(@Inject(MAT_DIALOG_DATA)private data:any,private dialogRef:MatDialogRef<BookDetailsComponent>, private routing: RoutingService) { }

  ngOnInit(): void {
  }

  gotoShop(){
    this.routing.goToShop();
  }

  closeBook(){
    this.dialogRef.close();
  }

}
