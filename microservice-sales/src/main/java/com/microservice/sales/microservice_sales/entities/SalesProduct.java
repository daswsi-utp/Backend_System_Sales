package com.microservice.sales.microservice_sales.entities;

import com.microservice.sales.microservice_sales.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_product")
public class SalesProduct {
    @EmbeddedId
    private SalesProductKey id = new SalesProductKey();

    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name = "fk_sale")
    private Sales Sale;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "fk_product")
    private ProductDTO product;

    @Column(nullable = false)
    private int quantity;
}
