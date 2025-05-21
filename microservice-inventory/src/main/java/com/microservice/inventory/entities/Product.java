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

public class    Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "nombre", nullable = false )
    private String nameProduct;

    @Column(name = "descripcion")
    private String descriptionProduct;

    @Column(name = "precio")
    private Double priceProduct;

    @Column(name = "cantidad")
    private Integer quantityProduct;

    @ManyToOne
    @JoinColumn(name = "marca_id", referencedColumnName = "id", nullable = true)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = true)
    private Category category;
}
