package com.microservice.registry.microservice_registry.service;

import com.microservice.registry.microservice_registry.entitites.Registry;

import java.util.List;

public interface IRegistryService {
    List<Registry> findAll();
    Registry findById(Long id);
    void save(Registry registry);
    void change(Long id, Registry registry);
}
