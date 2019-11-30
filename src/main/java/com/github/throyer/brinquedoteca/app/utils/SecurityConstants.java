package com.github.throyer.brinquedoteca.app.utils;

public class SecurityConstants {

    public static final String SECRET = "$2a$10$bEe1HPsWbp8YvmCDiJFGEORUFcXtg75BRQ./5t.UFztNSjOg55iYO";

    public static final String USERNAME_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";

    public static final Integer TOKEN_EXPIRATION = 86400;

    public static final String HOME_URL = "/";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_ERROR_URL = LOGIN_URL + "?error=true";
    public static final String ACESSO_NEGADO_URL = LOGIN_URL + "?negado=true";
    public static final String LOGOUT_URL = "/logout";
    
    public static final String SESSION_COOKIE_NAME = "JSESSIONID";
    
    public static final String[] STATICOS_IGNORADOS = {
        "/robots.txt",
        "/font/**",
        "/css/**",
        "/webjars/**",
        "/webjars/",
        "/js/**",
        "/favicon.ico"
    };
}
