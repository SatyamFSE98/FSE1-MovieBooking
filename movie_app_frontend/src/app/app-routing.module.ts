import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewMoviesComponent } from './view-movies/view-movies.component';
import { ViewTicketsComponent } from './view-tickets/view-tickets.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ResetComponent } from './reset/reset.component';
import { AuthGuardService } from './apiService/auth-guard.service';
import { AddMovieComponent } from './add-movie/add-movie.component';
import { BookMovieComponent } from './book-movie/book-movie.component';
import { MoviedetailsComponent } from './moviedetails/moviedetails.component';
import { NotfoundComponent } from './notfound/notfound.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponent },
  { path: 'reset', component: ResetComponent },
  { path: 'movies', component: ViewMoviesComponent, canActivate: [AuthGuardService], data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] } },
  { path: 'viewtickets', component: ViewTicketsComponent, canActivate: [AuthGuardService], data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] } },
  { path: 'addmovie', component: AddMovieComponent, canActivate: [AuthGuardService], data: { allowedRoles: ['ROLE_ADMIN'] } },
  { path: 'bookmovie', component: BookMovieComponent, canActivate: [AuthGuardService], data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] } },
  { path: 'movie/:id', component: MoviedetailsComponent, canActivate: [AuthGuardService], data: { allowedRoles: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] } },
  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
