package com.microservice.sales.microservice_sales.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SalesProductKey {
    @Column(name = "fk_sale")
    private Long saleId;
    @Column(name = "fk_product")
    private Long productId;
}
