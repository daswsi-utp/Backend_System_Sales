package com.microservice.inventory.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class    Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long idProduct;

    @Column(name = "name", nullable = false )
    private String nameProduct;

//    @Column(name = "descripcion")
//    private String descriptionProduct;

    @Column(name = "price")
    private Double priceProduct;

    @Column(name = "stock", nullable = false)
    private Integer quantityProduct;

    @ManyToOne
    @JoinColumn(name = "fk_brand", referencedColumnName = "brand_id", nullable = true)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "fk_category", referencedColumnName = "category_id", nullable = true)
    private Category category;

    //jack
    @Column
    private Long orderId;

    //jack
}
