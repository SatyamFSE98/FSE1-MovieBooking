import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MovieData } from '../model/movie-data';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieapiService } from '../apiService/movieapi.service';
import { AddmovieData } from '../model/addmovie-data';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent {
  movieForm: FormGroup | any;
  addMovieSuccess: boolean | null = false;
  loading:boolean = false;
  finalToken = this.authService.getUserToken();
  constructor(private formBuilder: FormBuilder,private authService:AuthapiService,private movieService:MovieapiService) {}

  ngOnInit() {
    this.movieForm = this.formBuilder.group({
      movieId: ['',Validators.required],
      movieName: ['', Validators.required],
      theaterName: ['', Validators.required],
      ticketStatus: ['', Validators.required],
      totalTickets: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.movieForm.invalid) {
      this.loading = false;
      return;
    }
    
    const formData = this.movieForm.value;
    console.log(formData);
    const movieData: AddmovieData = {
      movieId: formData.movieId,
      movieName: formData.movieName,
      theaterName: formData.theaterName,
      totalTickets: formData.totalTickets,
      ticketStatus: formData.ticketStatus,
    };
    this.movieService.addMovie(movieData,this.finalToken).subscribe(res=>{
   console.log(res);
   this.addMovieSuccess = true;
    },err=>{
      console.log(err.error);
      this.loading = true;
      // this.addMovieSuccess = true;
    });
    this.addMovieSuccess = false;
    this.loading = false;
    this.movieForm.reset();
  }

}
