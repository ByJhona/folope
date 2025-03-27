package com.byjhona.folope.autorizacao;

import jakarta.servlet.http.HttpServletResponse;

public class GerenciaCookie {

    public static void adicionarTokenCookie(HttpServletResponse response, String token) {
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

}
