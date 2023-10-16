import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MovieData } from '../model/movie-data';
import { TicketData } from '../model/ticket-data';
import { AddmovieData } from '../model/addmovie-data';

@Injectable({
  providedIn: 'root'
})
export class MovieapiService {

  
  movieServiceUrl = 'http://localhost:8081/api/v1.0/moviebooking';

  constructor(private http:HttpClient) { }
  //get all movies(admin+customer)
  getAllMovies(token:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.get<MovieData[]>(this.movieServiceUrl+"/getAllMovies",options);
  }

  //get the movie by movieId
  getMovieById(token:string,movieId:number){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.get<MovieData>(this.movieServiceUrl+`/movies/search/${movieId}`,options);//fix url here--match api gateway
  }

  //book a movie--fix me
  bookMovie(token:string,movieName:string,moviedata:TicketData){
    let options = {
      headers:{"Authorization":token}
    }//fix url here match api gateway
   return this.http.post<any>(this.movieServiceUrl+`/book/${movieName}`,moviedata,options);
  }

  //get ticket data of a user
  getUserTickets(token:string,userId:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.get<TicketData[]>(this.movieServiceUrl+`/getUserTickets/${userId}`,options);
  }
  //get all the tickets of all users
  getAllTickets(token:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.get<TicketData[]>(this.movieServiceUrl+"/getAllTickets",options);
  }

  //admin only : delete movie by it's id
  deleteMovie(movieId:number,token:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.delete<any>(this.movieServiceUrl+`/delete/${movieId}`,options);
  }
  //update the count of tickets:admin only
  updateTicketCount(movieName:string,sumTickets:number,token:string){
    let options = {
      headers:{"Authorization":token}
    }

    return this.http.put<any>(this.movieServiceUrl+`/${movieName}/update/${sumTickets}`,movieName,options);
  }
  //update the movie--v2 experimental
  updateMovie(movieName:string,movie:MovieData,token:string){
    let options = {
      headers:{"Authorization":token}
    }

    return this.http.put<any>(this.movieServiceUrl+`/update/${movieName}`,movie,options);
  }

  addMovie(movie:AddmovieData,token:string){
    let options = {
      headers:{"Authorization":token}
    }

    return this.http.post<AddmovieData>(this.movieServiceUrl+"/addmovie",movie,options);
  }

  

}
