import { Component } from '@angular/core';
import { AxiosService } from "../axios.service";

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css']
})
export class GamePlayComponent {
  data: string[] = [];

  constructor(private axiosService: AxiosService) {
    

  }
}
