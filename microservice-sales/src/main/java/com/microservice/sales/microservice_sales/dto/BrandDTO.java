package com.microservice.sales.microservice_sales.dto;
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
