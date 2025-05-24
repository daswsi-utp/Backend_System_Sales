package com.microservice.gateway.microservice_gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AppController
{
    @GetMapping("/authorized")
    public Map<String,String> authorizedMap(@RequestParam String code){
          Map<String, String> map =new HashMap<>();

          map.put("code",code);
          return map;


    }
    @PostMapping("/logout")
    public Map<String,String> logout(){
        return Collections.singletonMap("logout","ok");
    }
}
