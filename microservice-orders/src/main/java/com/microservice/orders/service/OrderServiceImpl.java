package com.microservice.orders.service;

import com.microservice.orders.client.ProductClient;
import com.microservice.orders.dto.ProductDTO;
import com.microservice.orders.entities.Order;
import com.microservice.orders.http.response.ProductByOrderResponse;
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
    private ProductClient productClient;
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
    public ProductByOrderResponse findProductByOrderId(Long orderId){

    // Consultar si existe el producto
    //Course course = courseRepository.findById(courseId).orElseThrow();
    Order order=orderRepository.findById(orderId).orElseThrow();
    // Obtener los estudiantes
    //List<ProductDTO> productDTOList = studentClient.findAllStudentByCourse(course.getId());
    List<ProductDTO>productDTOList=productClient.findAllProductByOrder(order.getId());
        return ProductByOrderResponse.builder()
                .nameStaff(order.getNameStaff())
                .total(order.getTotal())
                .dateTime(order.getDateTime())
                .status(order.getStatus())
                .productDTOList(productDTOList)
                .build();
    }

}