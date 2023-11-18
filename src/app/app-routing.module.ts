import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { GamePlayComponent } from './game-play/game-play.component';
import { GameplayHistoryComponent } from './gameplay-history/gameplay-history.component';

const routes: Routes = [
    {path: 'login', component: LoginFormComponent},
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'register', component: RegistrationFormComponent},
    {path: 'play', component: GamePlayComponent},
    {path: 'history', component: GameplayHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }