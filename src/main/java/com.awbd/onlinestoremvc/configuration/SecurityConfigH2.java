package com.awbd.onlinestoremvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
@Profile("h2")
public class SecurityConfigH2{




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("guest")
                .password(passwordEncoder.encode("12345"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("12345"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
////                        .requestMatchers("/products/**").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout.permitAll())
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .build();
    }
}

//@Configuration
////@EnableWebSecurity
////@EnableMethodSecurity
//@Profile("h2")
//public class SecurityConfigH2 {
////    protected void configure(HttpSecurity http) throws Exception {
////        http
//////                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/h2-console/**").permitAll()
////                        .requestMatchers("/products/**").permitAll()
////                        .anyRequest().authenticated()
////                )
//////                .headers(headers -> headers.frameOptions().sameOrigin())
//////                .httpBasic(withDefaults())
////                .formLogin(form -> form
////                        .loginPage("/showMyLoginPage")
////                        .loginProcessingUrl("/authenticateTheUser")
////                        .permitAll());
////    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails guest = User.builder()
//                .username("guest")
//                .password("{noop}12345")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}12345")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(guest,admin);
//    }
//


//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
////                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/products/**").permitAll()
//                        .anyRequest().authenticated()
//                )
////                .headers(headers -> headers.frameOptions().sameOrigin())
////                .httpBasic(withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/showMyLoginPage")
//                        .loginProcessingUrl("/authenticateTheUser")
//                        .permitAll())
//                .build();
//    }


//}


