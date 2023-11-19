import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = "http://localhost:8080/api/v1"
    axios.defaults.headers.post["Content-Type"] = "application/json"
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setUserData(fullName: string, token: string): void {
    if (fullName != null && token != null) {
      window.localStorage.setItem("fullName", fullName);
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("fullName");
      window.localStorage.removeItem("token");
    }
  }

  request(method: string, url: string, data: any): Promise<any> {

    if (this.getAuthToken() != null) {
      axios.defaults.headers.common['Authorization'] = "Bearer " + this.getAuthToken();
    }

    return axios({
      method: method,
      url: url,
      data: data
    })
  }

}
