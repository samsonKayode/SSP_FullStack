import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { GamePlayComponent } from './game-play/game-play.component';

const routes: Routes = [
    {path: 'login', component: LoginFormComponent},
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'register', component: RegistrationFormComponent},
    {path: 'play', component: GamePlayComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }