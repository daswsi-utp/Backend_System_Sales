package com.microservice.sales.microservice_sales.dto;

import lombok.*;

import java.util.List;

@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleRequestDTO {
    private double sum;
    private double tax;
    private Long registryId;
    private List<ProductQuantityDTO> products;
}
