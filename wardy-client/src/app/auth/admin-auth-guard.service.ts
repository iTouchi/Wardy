import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { AuthFirebaseService } from "./auth-firebase.service";
import { map, switchMap } from "rxjs/operators";
import { UserFirebaseService } from "../users/user-firebase.service";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class AdminAuthGuardService implements CanActivate {
  constructor(private auth: AuthFirebaseService, private userService: UserFirebaseService) {}

  canActivate(): Observable<boolean> {
return this.auth.appUser$.pipe(
      map((appUser) => appUser.isAdmin)
    );
  }
}
