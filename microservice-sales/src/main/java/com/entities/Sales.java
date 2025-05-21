package com.entities;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Getter
@Setter
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;
    private float sum;
    private float tax;
    @Column(name = "fk_registry")
    private Long idRegistro;

}