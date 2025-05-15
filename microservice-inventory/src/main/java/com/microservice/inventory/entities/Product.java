package com.microservice.inventory.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(table = "nombre", nullable = false )
    private String nameProduct;

    @Column(table = "descripcion")
    private String descriptionProduct;

    @Column(table = "precio")
    private Double priceProduct;

    @Column(table = "cantidad")
    private Integer quantityProduct;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = true)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    private Category category;
}
