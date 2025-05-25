package com.microservice.orders.persistence;

import com.microservice.orders.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT o from Order o WHERE o.productId =idProduct")
    List<Order>findAllOrder(Long idProduct);
}
