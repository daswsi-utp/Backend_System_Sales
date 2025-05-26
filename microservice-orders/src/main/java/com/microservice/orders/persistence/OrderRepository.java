package com.microservice.orders.persistence;

import com.microservice.orders.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
