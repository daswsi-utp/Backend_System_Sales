package com.microservice.sales.microservice_sales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long idSale;
    private double sum;
    private double tax;
    @OneToOne
    @JoinColumn(name = "fk_registry", referencedColumnName = "registry_id", nullable = false)
    private Registry registry;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesProduct> salesProductList;
    @Column(name = "gross_income")
    private double grossIncome;
}
