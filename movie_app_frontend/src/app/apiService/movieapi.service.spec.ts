import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MovieapiService } from './movieapi.service';

describe('MovieapiService', () => {
  let service: MovieapiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MovieapiService]
    });
    service = TestBed.inject(MovieapiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a GET request to retrieve all movies', () => {
    const token = 'test-token';
    const testMovieData =[{movieId:101,movieName:'endgame',theaterName:'pvr',totalTickets:2,ticketStatus:'book now',bookedSeats:['a1','a2'],tickets:[]}];

    service.getAllMovies(token).subscribe((movies) => {
      expect(movies).toEqual(movies);
    });

    const req = httpMock.expectOne(service.movieServiceUrl + '/getAllMovies');
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('Authorization')).toBe(token);
    req.flush(testMovieData);
  });

  it('should send a GET request to retrieve a movie by ID', () => {
    const token = 'arvind-token';
    const movieId = 123;
    const mockMovie={movieId:123,movieName:'endgame',theaterName:'pvr',totalTickets:2,ticketStatus:'book now',bookedSeats:['a1','a2'],tickets:[]};

    service.getMovieById(token, movieId).subscribe((movie) => {
      expect(movie).toEqual(mockMovie);
    });

    const req = httpMock.expectOne(service.movieServiceUrl + `/movies/search/${movieId}`);
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('Authorization')).toBe(token);
    req.flush(mockMovie);
  });
});
