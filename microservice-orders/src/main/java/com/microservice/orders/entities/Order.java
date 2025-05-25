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
    @Column(name = "name_staff")
    private String nameStaff;
    @Column(name = "order_date")
    private Timestamp dateTime;
    private Double total;
    private String status;
    @Column(name = "product_id")
    private Long productId;

}
