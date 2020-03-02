package com.github.throyer.brinquedoteca.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    /* obter email, senha e atividade */
    private static final String USERSBYUSERNAMEQUERY = "select email, senha, atividade from usuario where email=?";

    /* email e cargos do usuario */
    private static final String AUTHORITIESBYUSRNAMEQUERY = "select u.email, c.nome from usuario u inner join usuario_cargo uc on(u.id=uc.usuario_id) inner join cargo c on (uc.cargo_id = c.id) where u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(USERSBYUSERNAMEQUERY)
                .authoritiesByUsernameQuery(AUTHORITIESBYUSRNAMEQUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers(
                        "/login", "/", "/home", "/contato", "/pesquisar",
                        "/sugestao", "/canto/view/**", "/objeto/view/**",
                        "/install", "/tutorial/**", "/releases",
                        "/canto/exibe", "/canto/pesquisa", "/upload", "/imagem/objeto/capa/**"
                ).permitAll()
                .antMatchers("/conta", "/canto/**", "/objeto/**").hasAuthority("CURADOR")
                .antMatchers("/curador/**").hasAuthority("ADMINISTRADOR").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/login?negado=true");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/font/**",
                        "/css/**",
                        "/webjars/**",
                        "/webjars/",
                        "/js/**",
                        "/img/**",
                        "/img/tutorial/**",
                        "/favicon.ico");
    }

}
