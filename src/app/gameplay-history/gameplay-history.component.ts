import { Component, ViewChild, OnInit } from '@angular/core';
import { StorageService } from '../storage.service';
import { Router } from '@angular/router';
import { AxiosService } from '../axios.service';
import { ToastrService } from 'ngx-toastr';
import { VerifyService } from '../verify.service';

@Component({
  selector: 'app-gameplay-history',
  templateUrl: './gameplay-history.component.html',
  styleUrls: ['./gameplay-history.component.css']
})
export class GameplayHistoryComponent implements OnInit {

  constructor(private storageService: StorageService, private router: Router,
    private axiosService: AxiosService, private toastr: ToastrService, private verify: VerifyService) { }

  data: string[] = [];
  fullName: string = this.storageService.getData("fullName");
  page: number = 1;
  userHistory!: Array<any>;
  pages!: Array<number>;


  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.getHistory(i);
  }

  ngOnInit() {
    this.verify.verifyAccess();
    this.getHistory(this.page);
  }

  getHistory(page: number) {
    this.axiosService.request(
      "GET",
      "/play/history?pageNo=" + page,
      {}).then(
        (response) => {
          this.userHistory = response.data['content'];
          this.pages = new Array(response.data['totalPages']);
          console.log(response);
        }).catch(
          (error) => {
            if (error.response.status === 401) {
              this.logout();
            }
          });
  }


  logout() {
    this.storageService.clearData();
    this.router.navigate(['/login'])
  }

}

