package com.microservice.orders.entities;

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
public class OrderProductKey {
    @Column(name = "fk_order")
    private Long orderId;
    @Column(name = "fk_product")
    private Long productId;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof OrderProductKey that)) return false;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId,that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderId, productId);
    }
}
