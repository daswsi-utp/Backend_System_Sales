package com.microservice.orders.service;

import com.microservice.orders.dto.OrderProductDTO;
import com.microservice.orders.dto.OrderRequestDTO;
import com.microservice.orders.dto.OrderResponseDTO;

import java.util.List;

public interface IOrderService {
    List<OrderResponseDTO> findAll();
    OrderResponseDTO findById(Long id);
    void save(OrderRequestDTO orderRequestDTO);

    List<OrderProductDTO> getOrderProductDetails(Long orderId);
}
