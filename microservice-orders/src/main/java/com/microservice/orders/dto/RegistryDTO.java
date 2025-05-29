package com.microservice.orders.dto;

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
    private Long userId;
    private String templateUrl;
}
