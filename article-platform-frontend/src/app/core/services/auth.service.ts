import { Injectable } from '@angular/core';

import { HttpClient }
from '@angular/common/http';

import {
  Observable,
  tap
} from 'rxjs';

import {
  AuthResponse,
  LoginRequest,
  RegisterRequest
} from '../../models/auth.models';

import { environment }
from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl =
    `${environment.apiBaseUrl}/auth`;

  constructor(
    private http: HttpClient
  ) {
  }

  register(
    request: RegisterRequest
  ): Observable<AuthResponse> {

    return this.http.post<AuthResponse>(
      `${this.apiUrl}/register`,
      request
    );
  }

  login(
    request: LoginRequest
  ): Observable<AuthResponse> {

    return this.http.post<AuthResponse>(
      `${this.apiUrl}/login`,
      request
    ).pipe(

      tap(response => {

        localStorage.setItem(
          'token',
          response.token
        );
      })
    );
  }

  logout(): void {

    localStorage.removeItem('token');
  }

  getToken(): string | null {

    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {

    return !!this.getToken();
  }
}