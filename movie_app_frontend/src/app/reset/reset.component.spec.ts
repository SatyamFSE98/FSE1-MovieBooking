import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetComponent } from './reset.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AuthapiService } from '../apiService/authapi.service';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';

describe('ResetComponent', () => {
  let component: ResetComponent;
  let fixture: ComponentFixture<ResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HttpClientTestingModule,MatCardModule,MatFormFieldModule,MatSelectModule,FormsModule],
      providers: [AuthapiService],
      declarations: [ ResetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
