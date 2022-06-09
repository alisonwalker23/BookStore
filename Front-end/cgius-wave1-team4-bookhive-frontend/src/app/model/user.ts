export class User{
    id!:number;
    name:string;
    username: string;
    password: string;
    emailAddress:string;
    shippingAddress:string;
    role:string;

    constructor(){
      this.name = "";
      this.username= "";
      this.password= "";
      this.emailAddress="";
      this.role="";
      this.shippingAddress="";
    }
}