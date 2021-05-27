import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Product } from "../models/product";
import { switchMap, map } from "rxjs/operators";
import { ShoppingCartService } from "../shopping-cart/services/shopping-cart.service";
import { ProductFirebaseService } from "./services/product-firebase.service";
import { ProductRestService } from "./services/product-rest.service";
import { HttpClient } from "@angular/common/http";
import { ShoppingCartRestService } from "../shopping-cart/services/shopping-cart-rest.service";
import { Subscription, Observable } from "rxjs";
import { ShoppingCart } from "../models/shopping-cart";

@Component({
  selector: "app-products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css"],
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  category: string;
  cart$: Observable<ShoppingCart>;
  subscription: Subscription;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductRestService,
    private cartService: ShoppingCartService
  ) {    }

  async ngOnInit() {
    this.cart$ = await this.cartService.getCart();
    this.populateProducts();

  }

  private populateProducts() {
    this.productService
      .getAll()
      .pipe(
        switchMap((response) => {
          {
            this.products = response._embedded.productList;
          }
          return this.route.queryParamMap;
        })
      )
      .subscribe((params) => {
        this.category = params.get("category");
        this.applyFilter();
      });
  }

  private applyFilter() {
    this.filteredProducts = this.category
      ? this.products.filter((p) => p.category === this.category)
      : this.products;
  }

  // ngOnDestroy(){
  //   this.subscription.unsubscribe();
  // }
}
