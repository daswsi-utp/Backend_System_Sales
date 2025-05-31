package com.microservice.orders.service;

import com.microservice.orders.client.ProductClient;
import com.microservice.orders.client.RegistryClient;
import com.microservice.orders.dto.*;
import com.microservice.orders.entities.Order;
import com.microservice.orders.entities.OrderProduct;
import com.microservice.orders.entities.OrderProductKey;
import com.microservice.orders.entities.Registry;
import com.microservice.orders.persistence.OrderProductRepository;
import com.microservice.orders.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    //jack
    @Autowired
    private OrderProductRepository orderProductRepository;
    //jack
    @Autowired
    private ProductClient productClient;
    @Autowired
    private RegistryClient registryClient;

    @Override
    public List<OrderResponseDTO> findAll() {
        List<Order> ordersList = orderRepository.findAll();
        List<OrderResponseDTO> responseList = new ArrayList<>();
        for (Order order : ordersList) {
            RegistryDTO registryDTO = registryClient.getRegistryById(order.getRegistry().getIdRegistry());
            List<OrderProductDTO> products = getOrderProductDetails(order.getId());
            OrderResponseDTO responseDTO = OrderResponseDTO.builder()
                    .warehouse(order.getWarehouse())
                    .provider(order.getProvider())
                    .registry(registryDTO)
                    .relatedProducts(products)
                    .sum(order.getSum())
                    .status(order.getStatus())
                    .build();
            responseList.add(responseDTO);
        }
        return responseList;
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        RegistryDTO registryDTO = registryClient.getRegistryById(order.getRegistry().getIdRegistry());
        List<OrderProductDTO> products = getOrderProductDetails(order.getId());
        return OrderResponseDTO.builder()
                .warehouse(order.getWarehouse())
                .provider(order.getProvider())
                .registry(registryDTO)
                .relatedProducts(products)
                .sum(order.getSum())
                .status(order.getStatus())
                .build();
    }

    @Override
    public void save(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setWarehouse(orderRequestDTO.getWarehouse());
        order.setProvider(orderRequestDTO.getProvider());
        order.setSum(orderRequestDTO.getSum());
        order.setStatus(orderRequestDTO.getStatus());
        Registry registry = new Registry();
        registry.setIdRegistry(orderRequestDTO.getRegistryId());
        order.setRegistry(registry); // ¡Esta línea es la que faltaba!
        Order savedOrder = orderRepository.save(order);
        List<ProductQuantityDTO> stockUpdates = new ArrayList<>();
        for (ProductQuantityDTO productInfo : orderRequestDTO.getProducts()) {
            OrderProduct orderProduct = new OrderProduct();
            OrderProductKey key = new OrderProductKey();
            key.setOrderId(savedOrder.getId());
            key.setProductId(productInfo.getProductId());
            orderProduct.setId(key);
            orderProduct.setOrder(savedOrder);
            orderProduct.setQuantity(productInfo.getQuantity());
            orderProductRepository.save(orderProduct);
            stockUpdates.add(ProductQuantityDTO.builder()
                    .productId(productInfo.getProductId())
                    .quantity(productInfo.getQuantity())
                    .build());
        }
        productClient.increaseStock(stockUpdates);
    }

    @Override
    public List<OrderProductDTO> getOrderProductDetails(Long orderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        for (OrderProduct op : orderProducts) {
            ProductDTO productDTO = productClient.getProductById(op.getId().getProductId());
            if (productDTO == null) {
                throw new RuntimeException("Producto no encontrado con ID: " + op.getId().getProductId());
            }
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setProductId(productDTO.getIdProduct());
            orderProductDTO.setProductName(productDTO.getNameProduct());
            orderProductDTO.setPrice(productDTO.getPriceProduct());
            orderProductDTO.setQuantity(op.getQuantity());
            orderProductDTOS.add(orderProductDTO);
        }
        return orderProductDTOS;
    }
}