package com.microservice.sales.microservice_sales.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponseDTO {
    private long idSale;
    private double sum;
    private double tax;
    private RegistryDTO registry;
    private List<SalesProductDTO> relatedProducts;
    private double grossIncome;
}
