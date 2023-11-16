import { Component, Output, EventEmitter } from '@angular/core';
import { AxiosService } from '../axios.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

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

  registrationForm!: FormGroup;
  submitted = false;

  constructor(private axiosService : AxiosService, private toastr: ToastrService, 
    private router: Router, private formBuilder:FormBuilder) {}

    ngOnInit() {
      this.registrationForm = this.formBuilder.group({
        userName:['', Validators.required],
        password:['', Validators.required],
        fullName:['', Validators.required],
        email:['', Validators.email],
        phone:['', Validators.required]
      })
    }


  onSubmitRegister(): void {
    this.submitted = true;
    if(this.registrationForm.invalid) {
      return;
    }

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
