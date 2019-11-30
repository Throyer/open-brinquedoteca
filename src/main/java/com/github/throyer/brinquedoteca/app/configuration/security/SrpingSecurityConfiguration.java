package com.github.throyer.brinquedoteca.app.configuration.security;

import static com.github.throyer.brinquedoteca.app.utils.SecurityConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SrpingSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    SecurityService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Serviço de autenticação e Encoder.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /**
     * Configuração.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                /* urls publicas. */
                authorizeRequests()
                    .antMatchers(LOGIN_URL)
                        .permitAll()
                
                /* o restante precisa de login. */
                .anyRequest()
                    .authenticated()
                        .and()
                            .csrf()
                                .disable()
                
                /* fomulario de login. */
                .formLogin()
                    .loginPage(LOGIN_URL)
                        .failureUrl(LOGIN_ERROR_URL)
                            .defaultSuccessUrl(HOME_URL)
                                .usernameParameter(USERNAME_PARAMETER)
                                    .passwordParameter(PASSWORD_PARAMETER)
                                        .and()
                
                /* configuração do login permanente. */
                .rememberMe()
                    .key(SECRET)
                        .tokenValiditySeconds(TOKEN_EXPIRATION)
                            .and()
                
                /* configuração do logout. */
                .logout()
                    .deleteCookies(SESSION_COOKIE_NAME)
                        .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                            .logoutSuccessUrl(LOGIN_URL)
                                .and()
                                    .exceptionHandling()
                    
                /* configuração de acesso negado. */
                .accessDeniedPage(ACESSO_NEGADO_URL);
    }
    
    /**
     * Staticos.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(STATICOS_IGNORADOS);
    }
}
