package kz.bitlab.springsecurity.bootcamp4security.config;

import kz.bitlab.springsecurity.bootcamp4security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userService())
                .passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/403");

        http.formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/to-enter") // <form action = "/to-enter">
                .failureUrl("/sign-in?usererror") // return "redirect:/sign-in?error"; if error
                .defaultSuccessUrl("/profile") // return "redirect:/profile" if success
                .usernameParameter("user_email") // <input type = "email" name = "user_email">
                .passwordParameter("user_password"); // <input type = "password" name = "user_password">

        http.logout()
                .logoutUrl("/to-exit")
                .logoutSuccessUrl("/sign-in");

        http.csrf().disable();

        return http.build();
    }

}
