export class BookOrder{
    bookId: number;
    title: string;
    authorName: string;
    price: number;
    category: string;
    image:string;
    description:string;
    quantity: number;

    constructor(){
        this.bookId = 0;
        this.title = "";
        this.authorName = "";
        this.price = 0;
        this.category = "";
        this.quantity = 0;
        this.image="";
        this.description="";
    }
}
