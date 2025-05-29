package com.microservice.orders.service;

import com.microservice.orders.client.ProductClient;
import com.microservice.orders.dto.OrderProductDTO;
import com.microservice.orders.dto.ProductDTO;
import com.microservice.orders.entities.Order;
import com.microservice.orders.http.response.ProductByOrderResponse;
import com.microservice.orders.persistence.OrderProductRepository;
import com.microservice.orders.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    //jack
    @Autowired
    private OrderProductRepository orderProductRepository;
    //jack
    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<OrderProductDTO> getSaleProductDetails(Long saleId) {
        return List.of();
    }


}