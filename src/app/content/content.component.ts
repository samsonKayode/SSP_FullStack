import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {

  constructor(private axiosService : AxiosService) {}

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
    });
  }

}
