package com.thomas.JobApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);//implemented in MyUserDetailsService Class
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return  provider;
    }


    @Bean
    public SecurityFilterChain sercurityFilterChain(HttpSecurity http) throws Exception {

        http.
                csrf(customizer -> customizer.disable())   //This line disables Cross-Site Request Forgery (CSRF) protection
                .authorizeHttpRequests(request ->request.anyRequest().authenticated())  //It states that any HTTP request must be authenticated.
                .httpBasic(Customizer.withDefaults())  //This line enables HTTP Basic Authentication
                .sessionManagement(    //the application will not use HTTP sessions to store user authentication information.
                        session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
