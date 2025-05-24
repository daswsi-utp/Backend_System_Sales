package com.microservice.gateway.microservice_gateway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AppController
{
    @GetMapping("/authorized")
    public Map<String,String> authorizedMap(@RequestParam String codeString){
        return new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return "";
            }

            @Override
            public String put(String key, String value) {
                return "";
            }

            @Override
            public String remove(Object key) {
                return "";
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return Set.of();
            }

            @Override
            public Collection<String> values() {
                return List.of();
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return Set.of();
            }
        }
    }
}
