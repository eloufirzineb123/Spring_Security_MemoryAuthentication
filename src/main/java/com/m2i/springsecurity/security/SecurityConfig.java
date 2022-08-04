package com.m2i.springsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
    
  
    // Methode pour configurer la protection des url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .authorizeRequests()
              .antMatchers("/login")  // pour les requette qui matchant cette URI
              .permitAll()  //Autoriser toutes les requette==> pas besoin d'etre connecter'
             // .anyRequest().authenticated()  // pour toutes les autres requette demande une authentification
              .and()
              .formLogin()  // on active le formulaire login
              .loginPage("/login")  // on configire notre propre page de login plutots qu'utilise la page par defaut de spring security 
              //.successForwardUrl("/registration")  // redirection
              ;
              
              
              http.csrf().disable();
              http.headers().frameOptions().disable();
    }
  //Methode pour configurer la methode d'autentification
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .inMemoryAuthentication()  //authent en memoire donc pas de verif en basse
              .passwordEncoder(NoOpPasswordEncoder.getInstance()) //mot de pass crypté
              .withUser("admin").password("admin")  // on cree l'utilisateur amin
              .roles("USER", "ADMIN") //on lui assigner les role User et admin
              .and()
              .withUser("manager").password("manager")
             .roles("USER", "MANAGER");
    }



    
}
