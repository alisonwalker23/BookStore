import { Cart } from "./cart";

export class Order{
    orderId:number;
    userId: number;
    status:string;
    cart:Cart;


    constructor(){
        this.orderId = 0;
        this.userId = 0;
        this.status = "";
        this.cart = new Cart();
    }
}
