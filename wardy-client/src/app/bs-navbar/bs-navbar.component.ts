import { Component, OnInit } from "@angular/core";
import { AuthFirebaseService } from "../auth/auth-firebase.service";
import { AppUser } from "../models/app.user";
import { AuthRestService } from "../auth/auth-rest.service";
import { ShoppingCartService } from "../shopping-cart/services/shopping-cart.service";
import { Observable } from "rxjs";
import { ShoppingCart } from "../models/shopping-cart";

@Component({
  selector: "app-bs-navbar",
  templateUrl: "./bs-navbar.component.html",
  styleUrls: ["./bs-navbar.component.css"],
})
export class BsNavbarComponent implements OnInit {
  appUser: AppUser;
  cart$: Observable<ShoppingCart>;

  constructor(
    private auth: AuthRestService,
    private shoppingCartService: ShoppingCartService
  ) {}

  async ngOnInit() {
    this.auth.appUser$.subscribe((response) => (this.appUser = response));
    this.cart$ = await this.shoppingCartService.getCart();
  }

  logout() {
    this.auth.logout();
  }
}
