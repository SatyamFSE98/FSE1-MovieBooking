import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { MoviedetailsComponent } from './moviedetails.component';
import { RouterTestingModule } from '@angular/router/testing';
import { NavbarComponent } from '../navbar/navbar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieapiService } from '../apiService/movieapi.service';

describe('MoviedetailsComponent', () => {
  let component: MoviedetailsComponent;
  let fixture: ComponentFixture<MoviedetailsComponent>;
  const activatedRouteMock = {
    // Add any properties or methods you need for testing
    ngTempTokenPath: null, ngTokenPath: [ 'ActivatedRoute', 'ActivatedRoute' ] 
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,HttpClientTestingModule,MatCardModule,MatFormFieldModule,MatPaginatorModule,MatToolbarModule
      ],
      providers:[ { provide: ActivatedRoute, useValue: activatedRouteMock },AuthapiService,MovieapiService],
      declarations: [ MoviedetailsComponent,NavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MoviedetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
