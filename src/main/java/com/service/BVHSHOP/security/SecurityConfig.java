package com.service.BVHSHOP.security;

import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.service.CustomUserDetailsService;
import com.service.BVHSHOP.service.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * JwtAuthenticationFilter is a @Component, so Spring Boot would otherwise
     * auto-register it as a generic servlet filter on every URL, running
     * outside Spring Security's chain. That copy sets the SecurityContext too
     * early, SecurityContextHolderFilter then wipes it, and the copy placed
     * via addFilterBefore below gets silently skipped (OncePerRequestFilter's
     * already-filtered guard) — leaving requests unauthenticated even with a
     * valid token. Disabling the auto-registration keeps only the explicit
     * placement in the security chain.
     */
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegistration(JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * Main Spring Security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http


                // JWT is stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Authentication error handler
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint())).
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/images/**").permitAll()   // <-- allow public access to images
                        /*
                         * CORS preflight request
                         */.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        /*
                         * Login does NOT require JWT
                         *
                         * Example:
                         * POST /api/user-login/login
                         */.requestMatchers("/" + RouteController.USER_LOGIN + "/**").permitAll()

                        /*
                         * Everything else requires authentication
                         *
                         * Example:
                         * POST /api/category/create
                         * GET  /api/category/list
                         * PUT  /api/category/update
                         * DELETE /api/category/delete
                         */.anyRequest().authenticated()
                )
                // REST API + JWT does not use CSRF
                .csrf(csrf -> csrf.disable())

                /*
                 * Execute JWT filter before Spring's
                 * UsernamePasswordAuthenticationFilter
                 */.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Return 401 when authentication is required
     * but the user is not authenticated.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return (request, response, authException) -> {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                        "status": 401,
                        "code": "UNAUTHORIZED",
                        "message": "Authentication is required"
                    }
                    """);
        };
    }

    /**
     * Authentication provider used during login.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    /**
     * Authentication manager used by login.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }
}