package com.microservice.orders.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameStaff;
    @Column(name = "order_date")
    private Timestamp date;
    private Double total;
    private String status;
    @Column(name = "product_Id")
    private Long productId;

}
