import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthapiService } from '../apiService/authapi.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: FormGroup | any;
  hidePassword: boolean = true;
  loginSuccess: boolean | null = null;
  loading:boolean = false;
  hide:boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthapiService,
    private router:Router
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.loading = false;
      return;
    }
    this.loading = true;
    // Access form values
    const formValues = this.loginForm.value;
    console.log(formValues);
    this.authService.loginUser(formValues).subscribe(
      (res) => {
        console.log(res);
        localStorage.setItem('accessToken', res.accessToken);
        localStorage.setItem('username', res.username);
        localStorage.setItem('email', res.email);
        console.log(res.roles[0].name);
        localStorage.setItem('role', res.roles[0].name);
        console.log(JSON.stringify(res.roles[0].name));
        // localStorage.setItem('role',re)
        this.loginSuccess = true;
        this.loading = false;
        this.router.navigate(['/movies']);
      },
      (err) => {
        console.log(err.error);
        this.loginSuccess = false;
        this.loading = false;
      }
    );
  }
  // togglePasswordVisibility() {
  //   this.hidePassword = !this.hidePassword;
  // }

  // get passwordFieldType(): string {
  //   return this.hidePassword ? 'password' : 'text';
  // }
}
