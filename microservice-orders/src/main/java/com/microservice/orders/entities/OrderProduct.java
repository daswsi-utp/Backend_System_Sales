package com.microservice.orders.entities;

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
@Table(name = "order_product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey id = new OrderProductKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "fk_order", nullable = false)
    private Order order;



    @Column(nullable = false)
    private int quantity;

}
