package com.microservice.registry.microservice_registry.service;

import com.microservice.registry.microservice_registry.client.UserClient;
import com.microservice.registry.microservice_registry.dto.RegistryResponseDTO;
import com.microservice.registry.microservice_registry.dto.UserDTO;
import com.microservice.registry.microservice_registry.entitites.Registry;
import com.microservice.registry.microservice_registry.persistence.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistryServiceImp implements  IRegistryService{

    @Autowired
    private UserClient userClient;
    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public List<RegistryResponseDTO> findAll() {
        List<Registry> registries = registryRepository.findAll();
        List<RegistryResponseDTO> responseList = new ArrayList<>();

        for(Registry registry : registries){
            UserDTO userDTO = userClient.getUserById(registry.getUser().getIdUser());
            RegistryResponseDTO registryResponseDTO = RegistryResponseDTO.builder()
                    .idRegistry(registry.getIdRegistry())
                    .type(registry.getType())
                    .registrationDate(registry.getRegistrationDate())
                    .user(userDTO)
                    .url(registry.getTemplateUrl())
                    .build();
            responseList.add(registryResponseDTO);
        }
        return responseList;
    }

    @Override
    public RegistryResponseDTO findById(Long id) {
        Registry registry = registryRepository.findById(id).orElseThrow();
        UserDTO userDTO = userClient.getUserById(registry.getUser().getIdUser());
        return RegistryResponseDTO.builder().
                idRegistry(registry.getIdRegistry())
                .type(registry.getType())
                .registrationDate(registry.getRegistrationDate())
                .user(userDTO)
                .url(registry.getTemplateUrl())
                .build();
    }

    @Override
    public void save(Registry registry) {
        registryRepository.save(registry);
    }

    @Override
    public Registry change(Long id, Registry registry) {
        Optional<Registry> existingRegistry = registryRepository.findById(id);
        if(existingRegistry.isPresent()) {
            Registry updateRegistry = existingRegistry.get();
            updateRegistry.setUser(registry.getUser());
            updateRegistry.setType(registry.getType());
            updateRegistry.setTemplateUrl(registry.getTemplateUrl());
            updateRegistry.setRegistrationDate(registry.getRegistrationDate());
            return registryRepository.save(updateRegistry);
        }
        return null;

    }
}
