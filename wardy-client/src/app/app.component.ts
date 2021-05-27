import { Component } from "@angular/core";
import { AuthFirebaseService } from "./auth/auth-firebase.service";
import { Router } from "@angular/router";
import { UserFirebaseService } from "./users/user-firebase.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  constructor(
    private userService: UserFirebaseService,
    private auth: AuthFirebaseService,
    private router: Router
  ) {
    auth.user$.subscribe((user) => {
      if (!user) return;

      userService.save(user);

      let returnUrl = localStorage.getItem("returnUrl");
      if (!returnUrl) return;

      localStorage.removeItem("return");
      router.navigateByUrl(returnUrl);
    });
  }
}
