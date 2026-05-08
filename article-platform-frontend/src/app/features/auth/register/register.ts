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

import { RegisterRequest }
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
  selector: 'app-register',

  standalone: true,

  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],

  templateUrl: './register.html',

  styleUrl: './register.css'
})
export class RegisterComponent {

  registerForm;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {

    this.registerForm = this.fb.group({

      username: [
        '',
        Validators.required
      ],

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

    if (this.registerForm.invalid) {

      return;
    }

    const request =
      this.registerForm.value as RegisterRequest;

    this.authService.register(request)
      .subscribe({

        next: () => {

          this.router.navigate(['/login']);
        },

        error: error => {

          console.error(error);
        }
      });
  }
}