package com.microservice.gateway.microservice_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ReactiveJwtDecoder jwtDecoder) {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/security/login").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/public/**", "/authorized", "/logout").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN", "USER")
                        .pathMatchers(HttpMethod.GET, "/api/users/email/{email}").hasAnyRole("ADMIN", "USER")
                        .pathMatchers("/api/users/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtDecoder(jwtDecoder)
                                .jwtAuthenticationConverter(customJwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        // Cambia la URL al JWKS que expone tu microservicio OAuth Authorization Server
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://127.0.0.1:9100/oauth2/jwks").build();
    }

    private Converter<Jwt, Mono<? extends AbstractAuthenticationToken>> customJwtAuthenticationConverter() {
        return jwt -> {
            Collection<String> roles = jwt.getClaimAsStringList("roles");
            Collection<GrantedAuthority> authorities = roles == null ?
                    java.util.Collections.emptyList() :
                    roles.stream()
                            .map(role -> {
                                if (!role.startsWith("ROLE_")) {
                                    return new SimpleGrantedAuthority("ROLE_" + role);
                                } else {
                                    return new SimpleGrantedAuthority(role);
                                }
                            })
                            .collect(Collectors.toList());
            return Mono.just(new JwtAuthenticationToken(jwt, authorities));
        };
    }
}
