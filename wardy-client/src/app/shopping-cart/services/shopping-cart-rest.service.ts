import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Cart } from "src/app/models/cart";
import { Observable } from "rxjs";
import { Product } from "src/app/models/product";
import { ShoppingCartItem } from "src/app/models/shopping-cart-item";
import { take } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class ShoppingCartRestService {
  constructor(private http: HttpClient) {}

  // Add product to cart
 async addToCart(product: Product) {
    this.updateItem(product, 1);
  }


  // Update Item
  private async updateItem(product: Product, change: number) {

  }

  // get or create cart ID
  private async getOrCreateCartId() {
    let cartId = localStorage.getItem("cartId");
    if (cartId) return cartId;

    this.createNewCart().subscribe((response) => {
      localStorage.setItem("cartId", response.id);
      console.log(response);
      return this.getOne(response.id);
    });
  }

  // Create new cart
  private createNewCart(): Observable<any> {
    const dateCreated = new Date();

    return this.http.post<Cart>("http://localhost:8080/carts", dateCreated, {
      headers: { Authentication: "Bearer " + localStorage.getItem("jwt") },
    });
  }

  // Get one cart
  getOne(id: any): Observable<any> {
    return this.http.get<Cart>("http://localhost:8080/carts/" + id, {
      headers: { Authentication: "Bearer " + localStorage.getItem("jwt") },
    });
  }

  private getCartItem(cartId: string, productId: string) {
    return this.http.get<ShoppingCartItem>(
      "http://localhost:8080/carts/" + cartId + "/items",
      {
        headers: { Authentication: "Bearer " + localStorage.getItem("jwt") },
      }
    );
  }
}
