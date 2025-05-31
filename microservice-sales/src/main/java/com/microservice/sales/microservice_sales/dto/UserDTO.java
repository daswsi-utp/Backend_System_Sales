package com.microservice.sales.microservice_sales.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String lastName;
    private String email;
}
