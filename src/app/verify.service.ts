import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class VerifyService {

  constructor(private axiosService:AxiosService, private toastr: ToastrService, 
    private router:Router, private storageService: StorageService) { }

  verifyAccess() {
    this.axiosService.request(
      "GET",
      "/verify",
      {}).then(
        (response) => {
          
          console.log(response);
        }).catch(
          (error) => {
            if (error.response.status === 401) {
              this.toastr.error("You need to be logged in to access this page");
            } 
            this.storageService.clearData();
            this.router.navigate(['/login'])
          });
  }
}
