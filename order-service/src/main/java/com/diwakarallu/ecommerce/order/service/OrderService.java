package com.diwakarallu.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diwakarallu.ecommerce.order.dto.OrderRequest;
import com.diwakarallu.ecommerce.order.model.Order;
import com.diwakarallu.ecommerce.order.repository.OrderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        var order = mapToOrder(orderRequest);
        orderRepository.save(order);
    }

    private static Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}
