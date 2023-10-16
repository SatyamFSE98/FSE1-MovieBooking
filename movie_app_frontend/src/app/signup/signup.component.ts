import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SignupData } from '../model/signup-data';
import { AuthapiService } from '../apiService/authapi.service';
import { ResponseData } from '../model/response-data';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  registerForm: FormGroup | any;
  hidePassword: boolean = true;
  registrationSuccess: boolean | null = null;
  loading: boolean = false;//page load-progress bar

  constructor(private formBuilder: FormBuilder,private authService:AuthapiService) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      securityQuestion: ['', Validators.required],
      securityAnswer: ['', Validators.required],
    });
  }

  onSubmit() {  
    if (this.registerForm.invalid) {
      this.loading  = false;
      return;
    }
    this.loading = true;
    const formData = this.registerForm.value;
    console.log(formData);
    const signupData: SignupData = {
      username: formData.username,
      email: formData.email,
      password: formData.password,
      securityQuestion: formData.securityQuestion,
      securityAnswer: formData.securityAnswer,
    };
    this.authService.registerUser(signupData).subscribe(res=>{
      console.log(res.error.text);
      this.loading = false;
    },err=>{
      this.registrationSuccess = false;
      console.log(err.error);
      if(err.error.text==='User registered successfully'){  
        this.registrationSuccess = true;
      }else{
        this.registrationSuccess = false;
      }
      this.loading = false;
    })
    this.registerForm.reset();
   
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  get passwordFieldType(): string {
    return this.hidePassword ? 'password' : 'text';
  }
  // checkLogin():boolean{
  //   return this.loading;
  // }
}
