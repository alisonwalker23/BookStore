import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Order} from '../model/order';
import {OrderService} from '../services/order.service';


@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {
  orders: Order[] = [];
  order: Order = this.data;
  private dialog: any;
  status: string = "";

  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialogRef: MatDialogRef<UpdateOrderComponent>, private orderService: OrderService,) {
  }

  ngOnInit(): void {
  }

  editOrderStatus(order: Order) {
    this.orderService.updateAnOrder(this.order,).subscribe({
      next: () => {
        this.dialogRef.close();
      }
    })
  }


}





