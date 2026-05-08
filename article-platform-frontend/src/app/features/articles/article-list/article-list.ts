import {
  Component,
  OnInit
} from '@angular/core';

import { CommonModule }
from '@angular/common';

import { NavbarComponent }
from '../../../layout/navbar/navbar';

import { ArticleService }
from '../../../core/services/article.service';

import { ArticleSummary }
from '../../../models/article.models';

import { MatCardModule }
from '@angular/material/card';

import { RouterLink }
from '@angular/router';

@Component({
  selector: 'app-article-list',

  standalone: true,

  imports: [
    CommonModule,
    NavbarComponent,
    MatCardModule,
    RouterLink
  ],

  templateUrl: './article-list.html',

  styleUrl: './article-list.css'
})
export class ArticleListComponent
implements OnInit {

  articles: ArticleSummary[] = [];
  loading = true;;

  constructor(
    private articleService: ArticleService
  ) {
  }

  ngOnInit(): void {

    this.loadArticles();
  }

  loadArticles(): void {

    this.articleService.getArticles()
      .subscribe({

        next: response => {

          this.articles =
            response.content;
          
          this.loading = false;
        },

        error: error => {

          console.error(error);

          this.loading = false;
        }
      });
  }
}