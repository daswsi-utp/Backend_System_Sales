package com.microservice.registry.microservice_registry.controller;

import com.microservice.registry.microservice_registry.dto.RegistryResponseDTO;
import com.microservice.registry.microservice_registry.entitites.Registry;
import com.microservice.registry.microservice_registry.service.IRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {
    @Autowired
    private IRegistryService iRegistryService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Registry saveRegistry(@RequestBody Registry registry){
       return iRegistryService.save(registry);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllRegistry(){
        return ResponseEntity.ok(iRegistryService.findAll());
    }
    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(iRegistryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeRegistry(@PathVariable Long id, @RequestBody Registry registry){
        return ResponseEntity.ok(iRegistryService.change(id, registry));
    }
}
