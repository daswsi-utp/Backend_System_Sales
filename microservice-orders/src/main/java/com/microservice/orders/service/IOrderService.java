package com.microservice.orders.service;

import com.microservice.orders.dto.OrderProductDTO;
import com.microservice.orders.dto.OrderRequestDTO;
import com.microservice.orders.dto.OrderResponseDTO;
import com.microservice.orders.dto.UserDTO;
import com.microservice.orders.entities.provider;
import com.microservice.orders.entities.warehouse;
import com.microservice.orders.enums.OrderStatus;

import java.util.List;

public interface IOrderService {
    List<OrderResponseDTO> findAll();
    OrderResponseDTO findById(Long id);
    void save(OrderRequestDTO orderRequestDTO);
    List<warehouse> getAllWarehouses();
    List<provider> getAllProviders();
    List<OrderProductDTO> getOrderProductDetails(Long orderId);
    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);
    List<UserDTO> getAllUsers();





}
