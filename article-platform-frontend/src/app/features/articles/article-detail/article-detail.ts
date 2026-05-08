import {
  Component,
  OnInit
} from '@angular/core';

import { CommonModule }
from '@angular/common';

import {
  ActivatedRoute
} from '@angular/router';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { NavbarComponent }
from '../../../layout/navbar/navbar';

import { ArticleService }
from '../../../core/services/article.service';

import { CommentService }
from '../../../core/services/comment.service';

import { LikeService }
from '../../../core/services/like.service';

import {
  CommentResponse
} from '../../../models/comment.models';

import {
  MatCardModule
} from '@angular/material/card';

import {
  MatFormFieldModule
} from '@angular/material/form-field';

import {
  MatInputModule
} from '@angular/material/input';

import {
  MatButtonModule
} from '@angular/material/button';

@Component({
  selector: 'app-article-detail',

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

  templateUrl: './article-detail.html',

  styleUrl: './article-detail.css'
})
export class ArticleDetailComponent
implements OnInit {

  article: any;

  comments: CommentResponse[] = [];
  likeCount = 0;

  likeArticle(): void {

    this.likeService
      .likeArticle(this.articleId)
      .subscribe({
  
        next: () => {
  
          this.likeCount++;
        },
  
        error: error => {
  
          console.error(error);
        }
      });
  }

  commentForm;

  articleId!: number;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private commentService: CommentService,
    private fb: FormBuilder,
    private likeService: LikeService
  ) {

    this.commentForm = this.fb.group({

      content: [
        '',
        Validators.required
      ]
    });
  }

  ngOnInit(): void {

    this.articleId =
      Number(
        this.route.snapshot.paramMap.get('id')
      );

    this.loadArticle();

    this.loadComments();
  }

  loadArticle(): void {

    this.articleService
      .getArticleById(this.articleId)
      .subscribe({

        next: response => {

          this.article = response;
        },

        error: error => {

          console.error(error);
        }
      });
  }

  loadComments(): void {

    this.commentService
      .getCommentsByArticle(this.articleId)
      .subscribe({

        next: response => {

          this.comments = response;
        },

        error: error => {

          console.error(error);
        }
      });
  }

  submitComment(): void {

    if (this.commentForm.invalid) {

      return;
    }

    const request = {

      content:
        this.commentForm.value.content ?? ''
    };

    this.commentService
      .createComment(
        this.articleId,
        request
      )
      .subscribe({

        next: () => {

          this.commentForm.reset();

          this.loadComments();
        },

        error: error => {

          console.error(error);
        }
      });
  }
}