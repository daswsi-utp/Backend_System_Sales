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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registry_id")
    private Long idRegistry;
    private String type;
    @Column(name = "registration_date")
    private Timestamp registrationDate;
    @OneToOne
    @JoinColumn(name = "fk_user", referencedColumnName = "user_id", nullable = false)
    private User user;
    @Column(name = "template_url")
    private String templateUrl;
}
