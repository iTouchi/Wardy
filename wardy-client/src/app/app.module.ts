import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { CustomFormsModule } from 'ngx-custom-validators';
import { DataTableModule } from 'angular7-data-table';

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";

//firebase
import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";
import { AngularFireStorageModule } from "@angular/fire/storage";
import { AngularFireAuthModule } from "@angular/fire/auth";
import { environment } from "src/environments/environment";

//components
import { BsNavbarComponent } from "./bs-navbar/bs-navbar.component";
import { HomeComponent } from "./home/home.component";
import { ProductsComponent } from "./products/products.component";
import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";
import { CheckOutComponent } from "./check-out/check-out.component";
import { OrderSuccessComponent } from "./order-success/order-success.component";
import { MyOrdersComponent } from "./my-orders/my-orders.component";
import { AdminProductsComponent } from "./admin/admin-products/admin-products.component";
import { AdminOrdersComponent } from "./admin/admin-orders/admin-orders.component";
import { LoginComponent } from "./login/login.component";

//bootstrap
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

//auth
import { AuthFirebaseService } from "./auth/auth-firebase.service";
import { AuthGuardService as AuthGuard } from "./auth/auth-guard.service";
import { UserFirebaseService } from "./users/user-firebase.service";
import { AdminAuthGuardService as AdminAuthGuard } from "./auth/admin-auth-guard.service";
import { ProductFormComponent } from "./admin/product-form/product-form.component";
import { CategoryFirebaseService } from "./categories/services/category-firebase.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductFilterComponent } from './products/product-filter/product-filter.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { ShoppingCartService } from './shopping-cart/services/shopping-cart.service';
import { HttpClientModule } from '@angular/common/http'
import { ProductFirebaseService } from './products/services/product-firebase.service';
import { ProductRestService } from './products/services/product-rest.service';
import { CategoryRestService } from './categories/services/category-rest.service';
import { ProductQuantityComponent } from './product-quantity/product-quantity.component';


// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';



@NgModule({
  declarations: [
    AppComponent,
    BsNavbarComponent,
    HomeComponent,
    ProductsComponent,
    ShoppingCartComponent,
    CheckOutComponent,
    OrderSuccessComponent,
    MyOrdersComponent,
    AdminProductsComponent,
    AdminOrdersComponent,
    LoginComponent,
    ProductFormComponent,
    ProductFilterComponent,
    ProductCardComponent,
    ProductQuantityComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CustomFormsModule,
    DataTableModule,

    // firebase
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    AngularFireStorageModule,
    AngularFireAuthModule,

    //Bootstrap
    NgbModule,

    BrowserAnimationsModule,

    // FontAwesomeModule,

    // Rest
    HttpClientModule

  ],
  providers: [
    AuthFirebaseService,
    AuthGuard,
    AdminAuthGuard,
    UserFirebaseService,
    CategoryFirebaseService,
    CategoryRestService,
    ProductFirebaseService,
    ProductRestService,
    ShoppingCartService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
