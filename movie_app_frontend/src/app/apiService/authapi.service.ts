import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginData } from '../model/login-data';
import { SignupData } from '../model/signup-data';
import { ResetData } from '../model/reset-data';
import { ResponseData } from '../model/response-data';

@Injectable({
  providedIn: 'root',
})
export class AuthapiService {
  //authorization-microservice url
   authserviceUrl = 'http://localhost:8080/api/v1.0/auth';
  
  //authserviceUrl = ' https://hx9q0l7m81.execute-api.us-west-2.amazonaws.com/keertiauthdeploy';

  constructor(private http: HttpClient) { }

  loginUser(loginData: LoginData) {
    console.log(localStorage.getItem('username'));
    console.log(localStorage.getItem('password'));
    return this.http.post<any>(this.authserviceUrl + '/login', loginData);
  }

  registerUser(signupData: SignupData) {
    return this.http.post<any>(this.authserviceUrl + "/signup", signupData);
  }

  resetPasswordUser(resetData:ResetData){
    return this.http.patch<any>(this.authserviceUrl+"/forgot",resetData);
  }

  getUsersList(){
    return this.http.get<ResponseData[]>(this.authserviceUrl+"/getusers");
  }

  isUerLoggedIn(){
    return localStorage.getItem('accessToken')!==null;
  }
  isRoleAdmin(){
    const userRole =  localStorage.getItem('role');
    return userRole==='ROLE_ADMIN';
  }
  isRoleCustomer(){
    const userRole = localStorage.getItem('role');
    return userRole === 'ROLE_CUSTOMER';
  }
  getCurrentUserRoles(){
    const userRole = localStorage.getItem('role');
    if (userRole) {
      return userRole;
    }
    return '';
  }
  hasAnyRole(allowedRoles: string[]): boolean {
    const userRole = this.getCurrentUserRoles();
    return allowedRoles.includes(userRole);
  }

  logoutUser(){
    localStorage.clear();
  }

  getUserToken(){
    const token =  localStorage.getItem('accessToken');
    const finalToken = 'Bearer '+token;
    return finalToken;
  }

  getUsername(){
    const username = localStorage.getItem('username')
    return username;
  }
  
}
