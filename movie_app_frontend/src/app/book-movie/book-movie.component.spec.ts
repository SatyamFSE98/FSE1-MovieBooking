import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookMovieComponent } from './book-movie.component';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieapiService } from '../apiService/movieapi.service';

describe('BookMovieComponent', () => {
  let component: BookMovieComponent;
  let fixture: ComponentFixture<BookMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatSelectModule,HttpClientTestingModule,MatCardModule,MatSelectModule,MatToolbarModule],
      declarations: [BookMovieComponent,NavbarComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: new Map<string, string>().set('movieId', '1'),
            },
          },
        },
        AuthapiService,MovieapiService 
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BookMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});