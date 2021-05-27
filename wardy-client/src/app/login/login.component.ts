import { Component } from "@angular/core";
import { AuthFirebaseService } from "../auth/auth-firebase.service";
import { AuthRestService } from '../auth/auth-rest.service';

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent {
  constructor(private auth: AuthRestService) {}

  Batman: any = {
    username: "Batman",
  	password:"welkom01"
  };

  Robin: any = {
    username: "Robin",
  	password:"welkom01"
  };

  login() {
    this.auth.login(this.Batman);
  }

  login2() {
    this.auth.login(this.Robin);
  }
}
