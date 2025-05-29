package com.microservice.orders.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.sql.Timestamp;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long idOrder;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id", nullable = true)
    private warehouse warehouse;
    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id", nullable = true)
    private provider provider;

    private String status;


}
