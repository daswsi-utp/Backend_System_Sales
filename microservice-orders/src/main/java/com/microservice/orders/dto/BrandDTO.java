package com.microservice.orders.dto;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandDTO {
    private long  id;
    private String nameBrand;
}
