package com.microservice.orders.persistence;

import com.microservice.orders.entities.OrderProduct;
import com.microservice.orders.entities.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
    List<OrderProduct> findByOrderId(Long id);

}
