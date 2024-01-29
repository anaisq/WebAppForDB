package com.awbd.onlinestoremvc.configuration;

import com.awbd.onlinestoremvc.service.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("mysql")
public class SecurityJpaConfig {

    private final JpaUserDetailsService userDetailsService;

    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests((authz) -> authz
//////                                .requestMatchers("/products").permitAll()
//////                                .requestMatchers("/orders").hasAnyRole()
//////                                .requestMatchers("/products/**").hasRole("ADMIN")
//////                                .requestMatchers("/products/form").hasRole("ADMIN")
//////                                .requestMatchers("/login").permitAll()
//                        .anyRequest()
//                        .authenticated()
//                )
//                .userDetailsService(userDetailsService)
//                .formLogin((login) -> login
//                    .loginPage("/login")
//                    .loginProcessingUrl("/perform_login")
//                    .defaultSuccessUrl("/main.html", true)
//                    .failureUrl("/login.html?error=true")
////                            .failureHandler(authenticationFailureHandler())
//                )
//                .logout((logout) -> logout
//                    .logoutUrl("/perform_logout")
//                    .deleteCookies("JSESSIONID")
//                )
//                .exceptionHandling((exp) -> exp
//                        .accessDeniedPage("/access_denied")
//                );
//
////                .logoutSuccessHandler(logoutSuccessHandler());
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/products").permitAll()
//                                .requestMatchers("/orders").hasAnyRole()
//                                .requestMatchers("/products/**").hasRole("ADMIN")
//                                .requestMatchers("/products/form").hasRole("ADMIN")
//                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/perform_login")
                                .permitAll()
                )
                .exceptionHandling()
                .accessDeniedPage("/access_denied")
                .and()
                .logout(logout ->
                        logout
                                .logoutUrl("/perform_logout")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .permitAll()
                )
                .httpBasic(withDefaults())
                .build();
    }
}
