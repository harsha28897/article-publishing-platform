import { Component }
from '@angular/core';

import { Router }
from '@angular/router';

import { MatToolbarModule }
from '@angular/material/toolbar';

import { MatButtonModule }
from '@angular/material/button';

import { AuthService }
from '../../core/services/auth.service';

import { RouterLink }
from '@angular/router'

@Component({
  selector: 'app-navbar',

  standalone: true,

  imports: [
    MatToolbarModule,
    MatButtonModule,
    RouterLink
  ],

  templateUrl: './navbar.html',

  styleUrl: './navbar.css'
})
export class NavbarComponent {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  logout(): void {

    this.authService.logout();

    this.router.navigate(['/login']);
  }
}