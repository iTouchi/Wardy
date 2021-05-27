import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from 'src/app/models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryRestService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any>{
    return this.http.get<Category[]>("http://localhost:8080/categories", {
      headers: {'Authentication': 'Bearer ' + localStorage.getItem("jwt")}});
  }
}
