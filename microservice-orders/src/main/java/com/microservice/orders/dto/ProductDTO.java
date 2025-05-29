package com.microservice.orders.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
