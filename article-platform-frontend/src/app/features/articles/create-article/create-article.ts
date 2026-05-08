import { Component }
from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { Router }
from '@angular/router';

import { CommonModule }
from '@angular/common';

import { NavbarComponent }
from '../../../layout/navbar/navbar';

import { ArticleService }
from '../../../core/services/article.service';

import {
  CreateArticleRequest
} from '../../../models/article.models';

import { MatCardModule }
from '@angular/material/card';

import { MatFormFieldModule }
from '@angular/material/form-field';

import { MatInputModule }
from '@angular/material/input';

import { MatButtonModule }
from '@angular/material/button';

@Component({
  selector: 'app-create-article',

  standalone: true,

  imports: [
    CommonModule,
    ReactiveFormsModule,
    NavbarComponent,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],

  templateUrl: './create-article.html',

  styleUrl: './create-article.css'
})
export class CreateArticleComponent {

  articleForm;

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private router: Router
  ) {

    this.articleForm = this.fb.group({

      title: [
        '',
        Validators.required
      ],

      content: [
        '',
        Validators.required
      ]
    });
  }

  onSubmit(): void {

    if (this.articleForm.invalid) {

      return;
    }

    const request: CreateArticleRequest = {

      title:
        this.articleForm.value.title ?? '',
    
      content:
        this.articleForm.value.content ?? ''
    };

    this.articleService
      .createArticle(request)
      .subscribe({

        next: () => {

          this.router.navigate(['/articles']);
        },

        error: error => {

          console.error(error);
        }
      });
  }
}