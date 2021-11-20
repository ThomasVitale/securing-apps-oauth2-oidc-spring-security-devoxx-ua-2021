import { HttpClient, HttpXsrfTokenExtractor } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private location: Location, private httpXsrfTokenExtractor: HttpXsrfTokenExtractor) { }

  authenticate(): Observable<string> {
    return this.httpClient.get<string>('/welcome');
  }

  login(): void {
    window.open('/oauth2/authorization/keycloak', '_self');
  }

  logout(): Observable<any> {
    const formData: any = new FormData();
    formData.append('_csrf', this.httpXsrfTokenExtractor.getToken());
    return this.httpClient.post<any>('/logout', formData);
  }
}
