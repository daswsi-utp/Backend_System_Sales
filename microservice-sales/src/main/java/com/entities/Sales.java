package com.entities;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventas")
    private String id;
    @Column(name = "monto")
    private float sum;
    @Column(name = "cantidad")
    private int quantity;
    @Column(name = "fecha")
    private Date date;
    //TODO: Add User Class when it's ready
}