package com.microservice.users.microservice_users.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String country;

    private String city;

    private String state;

    private LocalDate birthDate;

    @NotBlank
    @Column(unique = true)
    private String password;
}
