import { Component, OnInit, ViewChild } from '@angular/core';
import { MovieData } from '../model/movie-data';
import { ActivatedRoute } from '@angular/router';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieapiService } from '../apiService/movieapi.service';
import { TicketData } from '../model/ticket-data';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-moviedetails',
  templateUrl: './moviedetails.component.html',
  styleUrls: ['./moviedetails.component.css']
})
export class MoviedetailsComponent implements OnInit {

  rows: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
  seats: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
  bookedSeats: string[] = [];
  selectedMovie: MovieData | any;
  tickets: TicketData[] | any;

  movieId: string | any;
  dataSource = new MatTableDataSource<TicketData>([]);
  userName: string | any;
  loading = true;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  finalToken = this.authService.getUserToken();

  constructor(private route: ActivatedRoute, private authService: AuthapiService, private movieService: MovieapiService) { }

  displayedColumns: string[] = [
    'transactionId',
    'numberOfTickets',
    'seatNumbers',
    'movieName',
    'theaterName',
    'userName'
  ];

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.movieId = params['id'];
      console.log(this.movieId);
      this.loadTicketsData();
    })
  }

  isSeatBooked(row: string, seat: string): boolean {
    return this.bookedSeats.indexOf(`${row}${seat}`) > -1;
  }

  loadTicketsData() {
    const userRole = this.authService.getCurrentUserRoles();
    if (userRole === 'ROLE_ADMIN') {
      this.loadAllTickets();
    } else if (userRole === 'ROLE_CUSTOMER') {
      this.userName = this.authService.getUsername();
      this.loadUsersTickets(this.userName);
    }

  }
  //for admin only
  loadAllTickets() {
    this.movieService.getMovieById(this.finalToken, this.movieId).subscribe(data => {
      console.log('searchmovieData: ', data);
      this.selectedMovie = data;
      this.tickets = data.tickets;
      // this.bookedSeats = [...data.bookedSeats];//insert into booked
      this.updateDataSource();
      console.log(this.tickets);
      this.loading = false;
    }, err => {
      console.log(err);
    });
  }
  //if userrole is customer
  loadUsersTickets(userId: string) {
    this.movieService.getMovieById(this.finalToken, this.movieId).subscribe(data => {
      console.log('searchmovieData: ', data);
      this.selectedMovie = data;
      this.tickets = data.tickets.filter(ticket => ticket.userName === this.userName);
      // this.tickets = data.tickets;
      this.bookedSeats = [...data.bookedSeats];//insert into booked
      this.updateDataSource();
      console.log(this.tickets);
      this.loading = false;
    }, err => {
      console.log(err);
    });
  }


  updateDataSource() {
    this.dataSource.data = this.tickets;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
