import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { ShopComponent } from './shop/shop.component';
import { HomepageComponent } from './homepage/homepage.component';
import { HeaderComponent } from './header/header.component';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SignupComponent } from './signup/signup.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { CanactivateGuard } from './guard/canactivate.guard';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { UpdateComponent } from './update/update.component';
import {MatDialogModule} from '@angular/material/dialog';
import { AdddeleteupdateComponent } from './adddeleteupdate/adddeleteupdate.component';
import { AccountComponent } from './account/account.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import { FooterComponent } from './footer/footer.component';
import { OrderComponent } from './order/order.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {MatTabsModule} from '@angular/material/tabs';
//import { SearchFilterPipe } from './search-filter.pipe';
import { ViewOrderComponent } from './view-order/view-order.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { ConfirmationPageComponent } from './confirmation-page/confirmation-page.component';



const routes: Routes = [

  {path:'shop', component:ShopComponent, canActivate:[CanactivateGuard]},
  {path:'login',component:LoginComponent},
  {path:'home', component:HomepageComponent},
  {path:'signup', component:SignupComponent},
  {path:'adddeleteupdate',component:AdddeleteupdateComponent},
   {path:'account',component:AccountComponent, canActivate:[CanactivateGuard]},
  {path:'cart',component:CartComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'order',component:OrderComponent},
  {path:'confirmationPage',component:ConfirmationPageComponent},
  {path:'',redirectTo:'home',pathMatch:'full'}
  

]


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ShopComponent,
    HomepageComponent,
    HeaderComponent,
    SignupComponent,
    UpdateComponent,
    AdddeleteupdateComponent,
    AccountComponent,
    CartComponent,
    CheckoutComponent,
    FooterComponent,
    OrderComponent,
    UpdateOrderComponent,
    ViewOrderComponent,
    BookDetailsComponent,
    ConfirmationPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    MatToolbarModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatSelectModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
    MatMenuModule,
    MatIconModule,
    MatPaginatorModule,
    Ng2SearchPipeModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
