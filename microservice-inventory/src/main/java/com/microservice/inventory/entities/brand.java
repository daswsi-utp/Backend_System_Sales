package com.microservice.inventory.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  idBrand;

    @Column(name = "nombre", nullable = false)
    private String nameBrand;


}
