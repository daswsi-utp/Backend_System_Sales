package com.microservice.oauth.microservice_oauth.services;

import com.microservice.oauth.microservice_oauth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersService  implements UserDetailsService {
    @Autowired
    private WebClient.Builder client;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Map<String,String> params = new HashMap<>();
        params.put("email",email);

        try{
            User user = client.build().get().uri("/email/{email}")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
            List<GrantedAuthority> roles = user.getRoles()
                    .stream()
                    .map( rol -> new SimpleGrantedAuthority(rol.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(),true,true,true,roles);
        }catch (WebClientException e){
            throw new UsernameNotFoundException( "error en el login , no existe el email " + email + "en el sistema");
        }
    }
}
