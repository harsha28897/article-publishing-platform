export interface ArticleSummary {

  id: number;

  title: string;

  authorUsername: string;
}

export interface ArticleResponse {

  id: number;

  title: string;

  content: string;

  authorUsername: string;

  createdAt: string;
}

export interface CreateArticleRequest {

  title: string;

  content: string;
}