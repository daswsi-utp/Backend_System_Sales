package com.microservice.orders.service;

import com.microservice.orders.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    Order findById(Long id);
    void save (Order order);

    List<Order> findByIdProduct(Long idProduct);
}
