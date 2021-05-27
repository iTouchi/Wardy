import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { UserFirebaseService } from "../users/user-firebase.service";
import { ActivatedRoute } from "@angular/router";
import { AppUser } from "../models/app.user";
import { switchMap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { UserRestService } from "../users/user-rest.service";

@Injectable({
  providedIn: "root",
})
export class AuthRestService {
  user: AppUser;
  user$: Observable<AppUser>;

  constructor(
    private userService: UserRestService,
    private http: HttpClient,
    private route: ActivatedRoute
  ) {}

  login(user: any) {
    let returnUrl = this.route.snapshot.queryParamMap.get("returnUrl") || "/";
    localStorage.setItem("returnUrl", returnUrl);

    this.http
      .post<any>("http://localhost:8080/authenticate", user)
      .subscribe((response) => localStorage.setItem("jwt", response.jwt));
  }

  logout(){
    console.log("before" + localStorage.getItem("jwt"))
    localStorage.removeItem("jwt");
    console.log("after" + localStorage.getItem("jwt"))
  }

  get appUser$(): Observable<AppUser> {
    const u = this.userService.get(localStorage.getItem("jwt")).subscribe((response) =>
    console.log(response));

    if (u) return this.userService.get(localStorage.getItem("jwt"));


    return of(null);
  }

}
