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
export class ProductRestService implements IProductService {



  constructor(private http: HttpClient) {}

  getAll(): Observable<any>{
    return this.http.get<Product[]>("http://localhost:8080/products", {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
  }

  createNewProduct(newProduct: Product): Observable<any> {
    return this.http.post<Product>("http://localhost:8080/products", newProduct, {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
  }
  getOne(id: any): Observable<any> {
    return this.http.get<Product>("http://localhost:8080/products/" +id, {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
  }
  update(id: any, product: Product) {
    return this.http.put<Product>("http://localhost:8080/products/" +id, product, {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
  }
  delete(id: any): Observable<any> {
    return this.http.delete<any>("http://localhost:8080/products/" +id, {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
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
