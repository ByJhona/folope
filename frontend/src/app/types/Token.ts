export interface Token {
  access_token: string;
  expires_in: number;
  not_before_policy: string;
  refresh_token: string;
  scope: string;
  session_state: string;
  token_type: string;
}
