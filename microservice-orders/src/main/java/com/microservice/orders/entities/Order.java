package com.microservice.orders.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_staff")
    private String nameStaff;
    private Double total;
    @Column(name = "order_date")
    private Timestamp dateTime;
    private String status;


}
