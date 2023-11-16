import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { GamePlayComponent } from './game-play/game-play.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ContentComponent } from './content/content.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    GamePlayComponent,
    WelcomePageComponent,
    LoginFormComponent,
    ContentComponent,
    RegistrationFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
