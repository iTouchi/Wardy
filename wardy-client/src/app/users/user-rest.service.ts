import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppUser } from '../models/app.user';

@Injectable({
  providedIn: 'root'
})
export class UserRestService {

  constructor(private http: HttpClient) {}

  // save(user: firebase.User) {
  //   this.db.object('/users/' + user.uid).update({
  //     name: user.displayName,
  //     email: user.email
  //   })
  // }

  get (token: string): Observable<AppUser> {
    return this.http.get<AppUser>("http://localhost:8080/one", {
      headers: {'Authentication': 'Bearer ' + token}});
  }
}
