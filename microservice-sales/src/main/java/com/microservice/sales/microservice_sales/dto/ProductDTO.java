package com.microservice.sales.microservice_sales.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long idProduct;

    private String nameProduct;

    private String descriptionProduct;

    private Double priceProduct;

    private Integer quantityProduct;

    private BrandDTO brand;

    private CategoryDTO category;
}
