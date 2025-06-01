package com.microservice.registry.microservice_registry.dto;


import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistryResponseDTO {
    private Long idRegistry;
    private String type;
    private Timestamp registrationDate;
    private UserDTO user;
    private String templateUrl;
}
