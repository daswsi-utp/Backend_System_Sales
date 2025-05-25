package com.microservice.gateway.microservice_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig
{
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        return serverHttpSecurity.authorizeExchange(authz -> {
            authz.pathMatchers("/authorized", "/logout").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/users").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN","USER")
                    .pathMatchers("/api/users/**").hasRole("ADMIN")
                    .anyExchange().authenticated();


        }).csrf(csrf -> csrf.disable())
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(ouath2 -> ouath2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(new Converter<Jwt, Mono<? extends AbstractAuthenticationToken>>() {
                            @Override
                            public Mono<? extends AbstractAuthenticationToken> convert(Jwt source) {
                                Collection<String> roles = source.getClaimAsStringList("roles");
                                Collection<GrantedAuthority> authorities = roles.stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList());
                                        return Mono.just(new JwtAuthenticationToken(source,authorities));
                            }
                        })
                ))
                .build();
    }
}
