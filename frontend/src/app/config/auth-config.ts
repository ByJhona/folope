import { AuthConfig } from 'angular-oauth2-oidc';

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8080',

  redirectUri: 'http://localhost:4200/home',
  clientId: 'folope-client',
  responseType: 'code',
 
  scope: 'openid profile offline_access',

  showDebugInformation: true,
};