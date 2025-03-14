import {AuthConfig } from 'angular-oauth2-oidc';

export const oAuthConfig:AuthConfig = {
    issuer: 'http://localhost:8081/realms/folope-realm',
    redirectUri: 'http://localhost:4200',
    clientId: 'folope-client',
    responseType: 'code',
    scope: "openid profile email offline_access",
    showDebugInformation:true
}