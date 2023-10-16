import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
import { ResetData } from '../model/reset-data';
import { AuthapiService } from '../apiService/authapi.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css'],
})
export class ResetComponent {
  forgotPasswordForm: FormGroup | any;
  hidePassword: boolean = true;
  resetSuccess: boolean | null = null;
  loading: boolean = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthapiService) { }

  ngOnInit() {
    this.forgotPasswordForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required],
      securityQuestion: ['', Validators.required],
      securityAnswer: ['', Validators.required]
    })
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() {
    if (this.forgotPasswordForm.invalid) {
      this.loading = false;
      return;
    }
    this.loading = true;
    console.log(this.forgotPasswordForm.value);
    const formData = this.forgotPasswordForm.value;
    const resetPasswordData: ResetData = {
      username: formData.username,
      newPassword: formData.password,
      secQuestion: formData.securityQuestion,
      secAnswer: formData.securityAnswer
    };

    console.log(resetPasswordData);
    this.authService.resetPasswordUser(resetPasswordData).subscribe(res => {
      console.log(res);
      if (res.msg === "Username doesn't exists!") {
        this.resetSuccess = false;
      }
      else {
        this.resetSuccess = true;
      }
      this.loading = false;
    }, err => {
      console.log('this is error')
      console.log(err.error);
      if(err.error.text==="changed password successfully!"){  
        this.resetSuccess = true;
      }else{
        this.resetSuccess = false;
      }
      this.loading = false;
    })
    this.forgotPasswordForm.reset();
    
  }
  passwordMatchCheck() {
    const password = this.forgotPasswordForm.get('password').value;
    const confirmPassword = this.forgotPasswordForm.get('confirmPassword').value;
    console.log('not matching');
    return password !== confirmPassword;
  }
}
