package com.microservice.orders.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "fk_warehouse", referencedColumnName = "id", nullable = true)
    private warehouse warehouse;

    @OneToOne
    @JoinColumn(name = "fk_provider", referencedColumnName = "id", nullable = true)
    private provider provider;

    @OneToOne
    @JoinColumn(name = "fk_registry", referencedColumnName = "registry_id", nullable = false)
    private Registry registry;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductList;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "status")
    private String status;


}
