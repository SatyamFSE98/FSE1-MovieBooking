import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ViewTicketsComponent } from './view-tickets.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTableModule } from '@angular/material/table';
import { AuthapiService } from '../apiService/authapi.service';
import { MovieapiService } from '../apiService/movieapi.service';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';

describe('ViewTicketsComponent', () => {
  let component: ViewTicketsComponent;
  let fixture: ComponentFixture<ViewTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers:[AuthapiService,MovieapiService],
      imports:[MatFormFieldModule,MatIconModule,HttpClientTestingModule,MatCardModule,MatPaginatorModule,MatTableModule,MatToolbarModule,MatProgressBarModule],
      declarations: [ ViewTicketsComponent,NavbarComponent,MatFormField ,MatLabel]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
