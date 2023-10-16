import { Component, ViewChild } from '@angular/core';
import { TicketData } from '../model/ticket-data';
import { MatTableDataSource } from '@angular/material/table';
import { MovieapiService } from '../apiService/movieapi.service';
import { MatPaginator } from '@angular/material/paginator';
import { AuthapiService } from '../apiService/authapi.service';

@Component({
  selector: 'app-view-tickets',
  templateUrl: './view-tickets.component.html',
  styleUrls: ['./view-tickets.component.css']
})
export class ViewTicketsComponent {

  tickets!: TicketData[];
  selectedMovie: any;
  loading = true;


  dataSource = new MatTableDataSource<TicketData>([]);
  finalToken = this.authService.getUserToken();
  userId: string | any;

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private movieService: MovieapiService, private authService: AuthapiService) { }

  displayedColumns: string[] = [
    'transactionId',
    'numberOfTickets',
    
    'movieName',
    'theaterName',
    'userId'
  ];
  ngOnInit() {
    this.loadTicketsData();//here actually we are loading data
  }
  showSeats(movie: any) {
    console.log(movie);
    this.selectedMovie = movie;
  }

  loadTicketsData() {
    const userRole = this.authService.getCurrentUserRoles();
    if (userRole === 'ROLE_ADMIN') {
      this.loadAllTickets();
    } else if (userRole === 'ROLE_CUSTOMER') {
      this.userId = this.authService.getUsername();
      this.loadUsersTickets(this.userId);
    }

  }

  loadAllTickets() {
    this.movieService.getAllTickets(this.finalToken).subscribe(data => {
      console.log(data);
      this.tickets = data;
      this.updateDataSource();
      console.log(this.tickets);
      this.loading = false;
    }, err => {
      console.log(err);
    })
  }
  
  loadUsersTickets(userName: any) {
    userName = this.authService.getUsername();
    this.movieService.getUserTickets(this.finalToken, userName).subscribe(data => {
      console.log(data);
      this.tickets = data;
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
