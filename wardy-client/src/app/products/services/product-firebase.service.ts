import { Injectable } from "@angular/core";
import { AngularFireDatabase } from "@angular/fire/database";
import { map, take, catchError } from "rxjs/operators";
import { Observable, throwError } from "rxjs";
import { Product } from "../../models/product";
import { HttpClient } from "@angular/common/http";
import { AppError } from "../../common/app-error";
import { BadInput } from "../../common/bad-input";
import { NotFoundError } from "../../common/not-found-error";
import { IProductService } from "./IProductService";

@Injectable({
  providedIn: "root",
})
export class ProductFirebaseService implements IProductService {

  constructor(private db: AngularFireDatabase) {}
  getOne(id: any) {
    throw new Error("Method not implemented.");
  }

  // get all Products
  getAll(): Observable<Product[]> {
    return this.db
      .list<Product>("/products")
      .snapshotChanges()
      .pipe(
        map((actions) =>
          actions.map((a) => ({ key: a.payload.key, ...a.payload.val() }))
        )
      );
  }

  // create new Product
  createNewProduct(newProduct: Product) {
    throw new Error("Method not implemented.");
  }
  GetOne(id: any): Observable<Product> {
    return this.db
      .object<Product>("/products/" + id)
      .valueChanges()
      .pipe(take(1));
  }

  update(productId, product) {
    this.db.object("/products/" + productId).update(product);
  }

  create(product) {
    this.db.list("/products").push(product);
  }

  delete(productId) {
    return this.db.object("/products/" + productId).remove();
  }

  private handleError(error: Response) {
    if (error.status === 404) {
      // return Observable.throw(new NotFoundError());
      return throwError(new NotFoundError());
    }
    if (error.status === 400) {
      // return Observable.throw(new BadInput(error.json()));
      return throwError(new BadInput(error));
    } else {
      // return Observable.throw(new AppError(error.json()));
      return throwError(new AppError(error));
    }
  }


}
