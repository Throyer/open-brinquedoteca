package com.github.throyer.brinquedoteca.modules.infra.configurations;

import com.github.throyer.brinquedoteca.modules.authentication.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.ACESSO_NEGADO_URL;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.DAY_MILLISECONDS;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.ENCODER;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.LOGIN_ERROR_URL;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.LOGIN_URL;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.LOGOUT_URL;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.PASSWORD_PARAMETER;
import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.USERNAME_PARAMETER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
  private final UserDetailsService userDetailsService;
  
  @Autowired
  public SecurityConfiguration(AuthenticationService authenticationService) {
    this.userDetailsService = authenticationService;
  }

  @Autowired
  protected void globalConfiguration(AuthenticationManagerBuilder authentication) throws Exception {
    authentication
      .userDetailsService(userDetailsService)
      .passwordEncoder(ENCODER);
  }
  
  @Bean
  public SecurityFilterChain app(HttpSecurity http) throws Exception {
    http
      .antMatcher("/**")
        .authorizeRequests()
          .antMatchers(
            GET,
            LOGIN_URL,
            "/",
            "/cantos",
            "/objetos",
            "/contato",
            "/image/**",
            "/webjars/**",
            "/js/**",
            "/css/**",
            "/font/**",
            "/img/**"
          )
            .permitAll()
          .antMatchers(POST, LOGIN_URL)
            .permitAll()
          .anyRequest()
            .authenticated()
      .and()
        .csrf()
          .disable()
            .formLogin()
              .loginPage(LOGIN_URL)
                .failureUrl(LOGIN_ERROR_URL)
                .defaultSuccessUrl("/")
              .usernameParameter(USERNAME_PARAMETER)
              .passwordParameter(PASSWORD_PARAMETER)
      .and()
        .rememberMe()
          .key("secret")
            .tokenValiditySeconds(DAY_MILLISECONDS)
      .and()
        .logout()
          .deleteCookies("OPEN_BRINQUEDOTECA")
            .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
            .logoutSuccessUrl(LOGIN_URL)
      .and()
        .exceptionHandling()
          .accessDeniedPage(ACESSO_NEGADO_URL);

    return http.build();
  }
}
