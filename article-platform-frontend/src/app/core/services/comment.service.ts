import { Injectable }
from '@angular/core';

import { HttpClient }
from '@angular/common/http';

import { Observable }
from 'rxjs';

import {
  CommentResponse,
  CreateCommentRequest
} from '../../models/comment.models';

import { environment }
from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private readonly apiUrl =
    `${environment.apiBaseUrl}/comments`;

  constructor(
    private http: HttpClient
  ) {
  }

  getCommentsByArticle(
    articleId: number
  ): Observable<CommentResponse[]> {

    return this.http.get<CommentResponse[]>(
      `${this.apiUrl}/article/${articleId}`
    );
  }

  createComment(
    articleId: number,
    request: CreateCommentRequest
  ): Observable<CommentResponse> {

    return this.http.post<CommentResponse>(
      `${this.apiUrl}/article/${articleId}`,
      request
    );
  }
}