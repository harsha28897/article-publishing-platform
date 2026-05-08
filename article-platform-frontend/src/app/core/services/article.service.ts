import { Injectable }
from '@angular/core';

import { HttpClient }
from '@angular/common/http';

import { Observable }
from 'rxjs';

import {
  ArticleSummary,
  CreateArticleRequest
} from '../../models/article.models';

import { environment }
from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private readonly apiUrl =
    `${environment.apiBaseUrl}/articles`;

  constructor(
    private http: HttpClient
  ) {
  }

  getArticles():
    Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}`
    );
  }

  createArticle(
    request: CreateArticleRequest
  ): Observable<any> {

    return this.http.post(
      `${this.apiUrl}`,
      request
    );
  }

  getArticleById(
      id: number
  ): Observable<any> {

    return this.http.get(
        `${this.apiUrl}/${id}`
    );
  }
}