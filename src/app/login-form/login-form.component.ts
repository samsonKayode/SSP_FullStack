import { Component, Output, EventEmitter, Input } from '@angular/core';
import { AxiosService } from '../axios.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  @Output() onSubmitLoginEvent = new EventEmitter();
  userName: string = "";
  password: string = "";

  onSubmitLogin(): void {
    //this.onSubmitLoginEvent.emit({"userName": this.userName, "password": this.password});
    this.onLogin({"userName": this.userName, "password": this.password})
  }

  constructor(private axiosService : AxiosService, private toastr: ToastrService, private router: Router) {}

  onLogin(input: any): void {
    this.axiosService.request(
      "POST",
      "/authenticate",
      {
        userName: input.userName,
        password: input.password
      }
    ).then(response => {
      this.axiosService.setAuthToken(response.data.token);
      this.toastr.success("You are logged in");
      this.router.navigate(["register"]);

    }).catch(error => {
      console.log(error);
      this.toastr.error("You have provided invalid login credentials");
    });
  }
}
