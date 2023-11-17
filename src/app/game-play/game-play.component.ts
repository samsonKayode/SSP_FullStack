import { Component } from '@angular/core';
import { AxiosService } from "../axios.service";
import { ToastrService } from 'ngx-toastr';
import { StorageService } from '../storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css']
})
export class GamePlayComponent {

  constructor(private axiosService: AxiosService, private toastr: ToastrService, 
    private storageService: StorageService, private router:Router) {}

  data: string[] = [];
  //playerMove: string = "";
  fullName: string = this.storageService.getData("fullName");
  userName: string = this.storageService.getData("userName");
  error_message: string ="";
  game_play_message: string = "Click a button to play!";
  game_play_status: string ="";
  playerScore: number = 0;
  computerScore: number = 0;

  onPlayGame(event: { target: any; srcElement: any; currentTarget: any; }) {
    var target = event.target || event.srcElement || event.currentTarget;
    var idAttr = target.attributes.id;
    var playerMove = idAttr.nodeValue;
    console.log("player move is "+playerMove);
    this.processGamePlay({ "playerMove": playerMove});
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
      if (response.data.winner == "WIN") {
        this.playerScore++;
        this.toastr.success("You ")
      }

      if (response.data.winner == "LOSS") {
        this.computerScore++;
        this.toastr.error("You Lost!");
      }

      this.game_play_status = response.data.winner;
      this.game_play_message = "Your played-> "+response.data.playerMove+", Computer played-> "+response.data.computerMove;
      console.log(response.data);
    }).catch(error => {
      if(error.response != null) {
        this.error_message = error.response.data.message;
      } else {
        this.error_message = "The application cannot connect to the server";
      }
      console.log(error);
      this.toastr.error(this.error_message);
    });
  }

  logout() {
    this.storageService.clearData();
    this.router.navigate(['/login'])
  }

}
