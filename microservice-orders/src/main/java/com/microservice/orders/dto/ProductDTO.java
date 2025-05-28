package com.microservice.orders.dto;
//COURSE == order
//studiante == product
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;
    private Integer quantityProduct;

    //jack
    private Long orderId;
    //jack
}
