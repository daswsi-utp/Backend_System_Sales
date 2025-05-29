package com.microservice.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
