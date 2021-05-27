import { Injectable } from "@angular/core";
import { AngularFireAuth } from "@angular/fire/auth";
import * as firebase from "firebase";
import { Observable, of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { AppUser } from '../models/app.user';
import { UserFirebaseService } from '../users/user-firebase.service';
import { map, switchMap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class AuthFirebaseService {
user$: Observable<firebase.User>;

  constructor(
    private userService: UserFirebaseService,
    private afAuth: AngularFireAuth,
    private route: ActivatedRoute) {
    this.user$ = afAuth.authState;
  }

  login() {
    let returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/';
    localStorage.setItem('returnUrl', returnUrl);

    this.afAuth.signInWithRedirect(new firebase.auth.GoogleAuthProvider());
  }

  logout() {
    this.afAuth.signOut();
  }

  get appUser$() : Observable<AppUser> {
    return this.user$.pipe(
      switchMap(user => {
        if (user) return this.userService.get(user.uid).valueChanges();
        return of(null);
      }));
  }
}

