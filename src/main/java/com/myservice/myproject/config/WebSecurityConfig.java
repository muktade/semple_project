package com.myservice.myproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.myservice.myproject.config.LoggingAccessDeniedHandler;

@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private LoggingAccessDeniedHandler loggingAccessDeniedHandler;

    private static final String[] WHITE_LIST_USER={
            "/hello", "/register", "/sendVerificationToken","/verifyUser"
    };


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .cors()
//                .and()
//                .csrf()
//                .disable()
                .authorizeRequests()
                .antMatchers("/login","/home","/home/*","/").permitAll()
                .antMatchers("/static/**", "/dist/**", "/css/**", "/fonts/**", "/js/**", "/images/**", "/ie8-panel/**").permitAll()
                .antMatchers(WHITE_LIST_USER).permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/user_login.html").permitAll()
                .and().formLogin().loginProcessingUrl("/login")
                .defaultSuccessUrl("/html/index.html",true)
                .failureUrl("/user_login.html?error=true")
//                .failureHandler(authenticationFailureHandler())
//                .successForwardUrl("/loginsuccess")
                .and().logout().deleteCookies()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedHandler(loggingAccessDeniedHandler);

        return http.build();
    }

}
