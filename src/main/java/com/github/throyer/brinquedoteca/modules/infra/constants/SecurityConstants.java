package com.github.throyer.brinquedoteca.modules.infra.constants;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConstants {
  private SecurityConstants() { }

  public static final Integer HASH_LENGTH = 10;
  public static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(HASH_LENGTH);
  
  public static final Integer DAY_MILLISECONDS = 86400;
  public static final String USERNAME_PARAMETER = "email";
  public static final String PASSWORD_PARAMETER = "password";
  public static final String LOGIN_URL = "/login";
  public static final String LOGIN_ERROR_URL = LOGIN_URL + "?error=true";
  public static final String ACESSO_NEGADO_URL = LOGIN_URL + "?denied=true";
  public static final String LOGOUT_URL = "/logout";
}
