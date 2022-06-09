import { Book } from "./book";

export class UserBook{
    
    userId:number;
    books:[
        {
        id:number,
        title:string,
        authorName: string,
        price:number,
        category:string,
        description:string,
        image:string,
        quantity: number
        
    }]

    constructor(){
        this.userId = 0;
        this.books = [{
            id:0,
            title:"",
            authorName:"",
            price:0,
            category:"",
            description:"",
            image:"",
            quantity:0
        }];
    }
}
