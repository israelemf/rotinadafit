package com.bytematrix.rotinadafit.configuration.security;

import com.bytematrix.rotinadafit.configuration.security.authentication.AuthFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
public class SecurityFilterConfiguration {
    public static final String [] ENDPOINTS_POST_NOT_REQUIRING_AUTHENTICATION = {
            "/users",
            "/user/auth"
    };

    public static final String [] ENDPOINTS_WITH_REQUIRING_AUTHENTICATION = {
            "/users"
    };

    private final AuthFilter authFilter;
    private final AuthenticationProvider authenticationUserProvider;

    public SecurityFilterConfiguration(AuthFilter authFilter, @Qualifier("authenticationUserProvider") AuthenticationProvider authenticationUserProvider) {
        this.authFilter = authFilter;
        this.authenticationUserProvider = authenticationUserProvider;
    }

    @Bean
    // SecurityFilterChain será conjunto de filtros que vamos aplicar nas nossas requisições, validando as autenticações
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_POST_NOT_REQUIRING_AUTHENTICATION).permitAll()
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_WITH_REQUIRING_AUTHENTICATION).authenticated()
                        .requestMatchers(HttpMethod.DELETE, ENDPOINTS_WITH_REQUIRING_AUTHENTICATION).authenticated()
                        .requestMatchers(HttpMethod.PUT, ENDPOINTS_WITH_REQUIRING_AUTHENTICATION).authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationUserProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
