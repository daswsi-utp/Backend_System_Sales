package com.microservice.orders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity; // Asegúrate de importar esta clase
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // Añadir esta anotación
public class warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Cambié el nombre a idWarehouse para mayor claridad

    @Column(name = "name")
    private String nameWarehouse;

    @Column(name = "address")
    private String address;
}
