package com.microservice.sales.microservice_sales.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesProductKey {
    @Column(name = "fk_sale")
    private Long saleId;
    @Column(name = "fk_product")
    private Long productId;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof SalesProductKey that)) return false;
        return Objects.equals(saleId, that.saleId) && Objects.equals(productId,that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(saleId, productId);
    }
}
