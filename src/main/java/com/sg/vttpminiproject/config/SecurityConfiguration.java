package com.sg.vttpminiproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration  {

    // have to implement filterchain due to deprecation of previous import
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.antMatcher("/**")
                .authorizeRequests()
                // specifies the paths that is allowed for access before login in with GitHub account
                .antMatchers("/", "/loginpage" , "/about" , "/contactus", "/success" , "/gallery")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // the path for login
                .oauth2Login().loginPage("/loginpage");
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
