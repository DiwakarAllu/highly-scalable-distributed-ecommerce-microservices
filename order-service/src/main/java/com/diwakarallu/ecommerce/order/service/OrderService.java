package com.diwakarallu.ecommerce.order.service;

import com.diwakarallu.ecommerce.order.dto.OrderRequest;
import com.diwakarallu.ecommerce.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderDetail(String orderNumber);

    void cancelOrder(String orderNumber);
}
