import { Component, Output, EventEmitter } from '@angular/core';
import { AxiosService } from '../axios.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
  @Output() onSubmitLoginEvent = new EventEmitter();
  userName: string = "";
  password: string = "";
  email: string= "";
  phone!: number;
  fullName: string = "";

  constructor(private axiosService : AxiosService, private toastr: ToastrService, private router: Router) {}


  onSubmitRegister(): void {
    this.onRegister({"userName": this.userName, "password": this.password
    , "email":this.email, "phone":this.phone, "fullName": this.fullName});
  }

  onRegister(input: any): void {
    this.axiosService.request(
      "POST",
      "/register",
      {
        userName: input.userName,
        password: input.password,
        email: input.email,
        phone: input.phone,
        fullName: input.fullName
      }
    ).then(response => {
      //this.axiosService.setAuthToken(response.data.token);
      this.toastr.success("Your account has been created "+ this.userName);
      this.router.navigate(["login"]);

    }).catch(error => {
      console.log(error);
      this.toastr.error("You have provided invalid data");
    });
  }

}
