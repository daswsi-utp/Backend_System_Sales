package com.microservice.gateway.microservice_gateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private ReactiveAuthenticationManager reactiveAuthenticationManager; // para login tradicional

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(authz -> authz
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/login", "/logout", "/authorized").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN", "USER")
                        .pathMatchers("/api/users/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authenticationManager(reactiveAuthenticationManager) // Para login tradicional
                .addFilterAt(loginAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION) // filtro para login JSON
                .oauth2Login(withDefaults())       // mantiene OAuth2 login
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(ouath2 -> ouath2.jwt(jwt -> jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter())))
                .build();
    }

    // Filtro para login tradicional JSON (email + password)
    public AuthenticationWebFilter loginAuthenticationWebFilter() {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(reactiveAuthenticationManager);
        filter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login"));
        filter.setServerAuthenticationConverter(new ServerAuthenticationConverter() {
            @Override
            public Mono<Authentication> convert(ServerWebExchange exchange) {
                return exchange.getRequest().getBody().next()
                        .flatMap(dataBuffer -> {
                            try {
                                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(bytes);
                                DataBufferUtils.release(dataBuffer);
                                String bodyString = new String(bytes, StandardCharsets.UTF_8);
                                ObjectMapper mapper = new ObjectMapper();
                                LoginRequest loginRequest = mapper.readValue(bodyString, LoginRequest.class);
                                UsernamePasswordAuthenticationToken authToken =
                                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
                                return Mono.just(authToken);
                            } catch (Exception e) {
                                return Mono.empty();
                            }
                        });
            }
        });
        filter.setAuthenticationSuccessHandler(new ServerAuthenticationSuccessHandler() {
            @Override
            public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
                ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
                response.setStatusCode(HttpStatus.OK);
                DataBuffer dataBuffer = response.bufferFactory().wrap("{\"status\":\"success\"}".getBytes());
                return response.writeWith(Mono.just(dataBuffer));
            }
        });
        filter.setAuthenticationFailureHandler(new ServerAuthenticationFailureHandler() {
            @Override
            public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
                ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                DataBuffer dataBuffer = response.bufferFactory().wrap("{\"status\":\"error\",\"message\":\"Credenciales inválidas\"}".getBytes());
                return response.writeWith(Mono.just(dataBuffer));
            }
        });
        return filter;
    }

    // Converter roles del JWT a GrantedAuthority (igual que tienes)
    private Converter<Jwt, Mono<? extends AbstractAuthenticationToken>> customJwtAuthenticationConverter() {
        return jwt -> {
            Collection<String> roles = jwt.getClaimAsStringList("roles");
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return Mono.just(new JwtAuthenticationToken(jwt, authorities));
        };
    }

    // Clase para deserializar JSON login
    static class LoginRequest {
        private String email;
        private String password;

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }
    }

}

