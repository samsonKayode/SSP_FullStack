import { Component, Output, EventEmitter, Input } from '@angular/core';
import { AxiosService } from '../axios.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  @Output() onSubmitLoginEvent = new EventEmitter();
  userName: string = "";
  password: string = "";



  loginForm!: FormGroup;
  submitted = false;

  constructor(private axiosService: AxiosService, private toastr: ToastrService,
    private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(3)]]
    })
  }

  onSubmitLogin(): void {
    this.submitted=true;
    if(this.loginForm.invalid) {
      return;
    }
    this.onLogin({ "userName": this.userName, "password": this.password })
  }

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
