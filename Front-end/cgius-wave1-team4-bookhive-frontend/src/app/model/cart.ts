import {BookOrder} from "./bookOrder";

export class Cart{
    id: number;
    userId: number;
    bookOrders:  BookOrder[];

    constructor(){
        this.id = 0,
        this.userId = 0,
        this.bookOrders = []

    }
}
