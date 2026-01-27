package com.rafael.itmanager.config;

import com.rafael.itmanager.security.CustomUserDetailsService;
import com.rafael.itmanager.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/usuarios/**"))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/h2-console/**").permitAll();
                    authorize.requestMatchers("/usuarios/**").permitAll();
                    authorize.requestMatchers("/colaboradores/**").authenticated();
                    authorize.requestMatchers("/equipamentos/**").authenticated();
                    authorize.requestMatchers("/emprestimos/**").authenticated();

                    authorize.anyRequest().permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

                http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
                return  http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService service) {
        return new CustomUserDetailsService(service);
    }
}
