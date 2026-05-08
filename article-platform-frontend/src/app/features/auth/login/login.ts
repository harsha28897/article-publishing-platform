import { Component } from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { Router } from '@angular/router';

import { CommonModule }
from '@angular/common';

import { AuthService }
from '../../../core/services/auth.service';

import { LoginRequest }
from '../../../models/auth.models';

import { MatCardModule }
from '@angular/material/card';

import { MatFormFieldModule }
from '@angular/material/form-field';

import { MatInputModule }
from '@angular/material/input';

import { MatButtonModule }
from '@angular/material/button';

@Component({
  selector: 'app-login',

  standalone: true,

  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],

  templateUrl: './login.html',

  styleUrl: './login.css'
})
export class LoginComponent {

  loginForm;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {

    this.loginForm = this.fb.group({

      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],

      password: [
        '',
        Validators.required
      ]
    });
  }

  onSubmit(): void {

    if (this.loginForm.invalid) {

      return;
    }

    const request =
      this.loginForm.value as LoginRequest;

    this.authService.login(request)
      .subscribe({

        next: () => {

          this.router.navigate(['/articles']);
        },

        error: error => {

          this.errorMessage = 'Invalid credentials';
        }
      });
  }
}