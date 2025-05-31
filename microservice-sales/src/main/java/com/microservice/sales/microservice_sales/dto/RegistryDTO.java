package com.microservice.sales.microservice_sales.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistryDTO {
    private String type;
    private Timestamp registrationDate;
    private UserDTO user;
    private String templateUrl;
}
