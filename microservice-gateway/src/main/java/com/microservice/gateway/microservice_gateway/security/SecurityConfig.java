package com.microservice.gateway.microservice_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig
{
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        return serverHttpSecurity.authorizeExchange(authz -> {
            authz.pathMatchers("/authorized", "/logout").permitAll().pathMatchers(HttpMethod.GET, "/api/users").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN")
                    .pathMatchers("/api/users/**").hasAnyRole("ADMIN")
                    .anyExchange().authenticated();


        }).cors(csrf -> csrf.disable())
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(withDefaults())
                .build();
    }
}
