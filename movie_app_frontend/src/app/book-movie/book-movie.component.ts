import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieapiService } from '../apiService/movieapi.service';
import { TicketData } from '../model/ticket-data';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieData } from '../model/movie-data';

@Component({
  selector: 'app-book-movie',
  templateUrl: './book-movie.component.html',
  styleUrls: ['./book-movie.component.css']
})
export class BookMovieComponent {
  // rows: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
  // seats: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
  // seatNumbers: string[] = [];
  // bookedSeats: string[] = ['A1'];
  
  selectedMovie:MovieData|any;
  bookingSuccess:boolean|null = false;
  notbookingSuccess:boolean|null = false;
  loading:boolean = false;

  movieName:string|any;
  theaterName:string|any;
  movieId:string|any;
  bookingForm: FormGroup;
  


 
  finalToken = this.authService.getUserToken();
  username:string|any;
  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router, private movieService: MovieapiService, private authService: AuthapiService) {
    this.bookingForm = this.fb.group({
      movieName: ['', Validators.required],
      theaterName: ['', Validators.required],
      numberOfTickets: [null, Validators.required],
     });
  }

  onSubmit(): void {
    this.username= this.authService.getUsername();
    const bookingData: TicketData = {
      transactionId:'',//sending empty because will set it from backend
      numberOfTickets: this.bookingForm.controls['numberOfTickets'].value,
      movieName: this.bookingForm.controls['movieName'].value,
      theaterName: this.bookingForm.controls['theaterName'].value,
      userName:this.username//will take from local storage
    };
    //book the movie here--fix--prt-mid
    if (this.bookingForm.invalid) {
      this.loading = false;
      return;
    }
    this.loading = true;
    this.movieService.bookMovie(this.finalToken,this.movieName,bookingData).subscribe(res=>{
      console.log(res);
      this.loading = false;
      window.location.reload();
      this.bookingForm.reset();
      // if(res.err)
    },err=>{
      this.loading = false;
      console.log(err.error.text);
      console.log('Successfully booked movie '+this.movieName);
      if(err.error.text==='Successfully booked movie '+this.movieName){
        this.bookingSuccess = true;
      }else{
        this.notbookingSuccess = true;

      }
      
      console.log(err);
    });
    this.bookingSuccess = false;
    this.notbookingSuccess = false;
    console.log(bookingData);

  }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.movieName = params['movieName'];
      this.theaterName = params['theaterName'];
      this.movieId = params['movieId'];
      console.log(params);
      this.bookingForm.setValue({
        movieName: this.movieName,
        theaterName: this.theaterName,
        numberOfTickets: this.bookingForm.controls['numberOfTickets'].value
      });
    });
    //it will search for movie by id and show booked seats in box
    this.movieService.getMovieById(this.finalToken,this.movieId).subscribe(data=>{
      console.log('movie searching....byid');
      console.log(data);
      this.selectedMovie = data;
      // this.bookedSeats = [...data.bookedSeats];//insert into booked
      
    },err=>{
      console.log(err);
    })
  }
  
 

}
