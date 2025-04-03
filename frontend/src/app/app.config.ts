import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { NaoAutorizadoInterceptor } from './interceptors/loginInterceptor';
import { provideOAuthClient } from 'angular-oauth2-oidc';



export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideClientHydration(),  provideOAuthClient(),provideHttpClient(withFetch(), withInterceptors([NaoAutorizadoInterceptor])), provideAnimationsAsync()]
};
