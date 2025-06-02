package com.microservice.registry.microservice_registry.service;

import com.microservice.registry.microservice_registry.dto.RegistryResponseDTO;
import com.microservice.registry.microservice_registry.entitites.Registry;

import java.util.List;

public interface IRegistryService {
    List<RegistryResponseDTO> findAll();
    RegistryResponseDTO findById(Long id);
    Registry save(Registry registry);
    Registry change(Long id, Registry registry);
}
