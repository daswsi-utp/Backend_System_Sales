package com.microservice.orders.service;

import com.microservice.orders.dto.OrderProductDTO;
import com.microservice.orders.entities.Order;
import com.microservice.orders.http.response.ProductByOrderResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    Order findById(Long id);
    void save (Order order);

    List<OrderProductDTO> getSaleProductDetails(Long saleId);
}
