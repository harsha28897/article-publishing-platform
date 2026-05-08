import { Routes } from '@angular/router';

import { LoginComponent }
from './features/auth/login/login';

import { RegisterComponent }
from './features/auth/register/register';

import { ArticleListComponent }
from './features/articles/article-list/article-list';

import { authGuard } 
from './core/guards/auth-guard';

import { CreateArticleComponent }
from './features/articles/create-article/create-article';

import { ArticleDetailComponent }
from './features/articles/article-detail/article-detail'

import { from } from 'rxjs';

export const appRoutes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'register',
    component: RegisterComponent
  },

  {
      path: 'articles',
      component: ArticleListComponent,
      canActivate: [authGuard]
  },

  {
    path: 'articles/create',
    component: CreateArticleComponent,
    canActivate: [authGuard]
  },
  
  {
    path: 'articles/:id',
    component: ArticleDetailComponent,
    canActivate: [authGuard]
  }
];