import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Book } from '../model/book';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  errorMessage = "";
  book : Book = this.data;
  constructor(@Inject(MAT_DIALOG_DATA)private data:any,  private dialogRef:MatDialogRef<UpdateComponent>, private productService:ProductService) { }

  ngOnInit(): void {
  }

  edit(book:Book){
    this.productService.updateBook(this.book).subscribe({
      next:()=>{
        this.dialogRef.close();
      }
    })
  }
}
