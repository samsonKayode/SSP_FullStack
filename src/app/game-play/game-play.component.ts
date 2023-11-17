import { Component } from '@angular/core';
import { AxiosService } from "../axios.service";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css']
})
export class GamePlayComponent {
  data: string[] = [];
  playerMove: string = "";

  constructor(private axiosService: AxiosService, private toastr: ToastrService) {}

  onPlayGame() {
    this.processGamePlay({ "playerMove": this.playerMove});
  }

  processGamePlay(input: any): void {
    this.axiosService.request(
      "POST",
      "/play",
      {
        playerMove: input.playerMove,
      }
    ).then(response => {
      this.data = response;
    }).catch(error => {
      console.log(error);
      this.toastr.error("Game play has encountered an error");
    });
  }
}
