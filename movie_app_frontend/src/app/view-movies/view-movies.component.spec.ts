import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMoviesComponent } from './view-movies.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MovieapiService } from '../apiService/movieapi.service';
import { AuthapiService } from '../apiService/authapi.service';
import { MatDialog } from '@angular/material/dialog';

describe('ViewMoviesComponent', () => {
  let component: ViewMoviesComponent;
  let fixture: ComponentFixture<ViewMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers: [AuthapiService,MatDialog],
      declarations: [ ViewMoviesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
