import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection
} from '@angular/core';

import { provideRouter }
from '@angular/router';

import { appRoutes }
from './app.routes';

import {
  provideHttpClient,
  withInterceptors
} from '@angular/common/http';

import {
  jwtInterceptor
} from './core/interceptors/jwt-interceptor';

export const appConfig: ApplicationConfig = {

  providers: [

    provideBrowserGlobalErrorListeners(),

    provideZoneChangeDetection({
      eventCoalescing: true
    }),

    provideRouter(appRoutes),

    provideHttpClient(
      withInterceptors([
        jwtInterceptor
      ])
    )
  ]
};