package com.microservice.sales.microservice_sales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registry")
public class Registry {
    @Id
    @Column(name = "registry_id")
    private Long idRegistry;
}
