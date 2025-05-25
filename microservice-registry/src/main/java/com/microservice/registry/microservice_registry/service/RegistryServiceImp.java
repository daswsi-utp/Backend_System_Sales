package com.microservice.registry.microservice_registry.service;

import com.microservice.registry.microservice_registry.entitites.Registry;
import com.microservice.registry.microservice_registry.persistence.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistryServiceImp implements  IRegistryService{

    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public List<Registry> findAll() {
        return registryRepository.findAll();
    }

    @Override
    public Registry findById(Long id) {
        return registryRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Registry registry) {
        registryRepository.save(registry);
    }

    @Override
    public void change(Long id, Registry registry) {
        Optional<Registry> existingRegistry = registryRepository.findById(id);
        if(existingRegistry.isPresent()) {
            Registry updateRegistry = existingRegistry.get();
            updateRegistry.setUser(registry.getUser());
            updateRegistry.setType(registry.getType());
            updateRegistry.setTemplateUrl(registry.getTemplateUrl());
            updateRegistry.setRegistrationDate(registry.getRegistrationDate());
        }
    }
}
