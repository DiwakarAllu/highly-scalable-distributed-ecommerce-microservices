package com.diwakarallu.ecommerce.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.diwakarallu.ecommerce.order.dto.OrderRequest;
import com.diwakarallu.ecommerce.order.dto.OrderResponse;
import com.diwakarallu.ecommerce.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable String orderNumber) {
        return orderService.getOrderDetail(orderNumber);
    }

    @DeleteMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@PathVariable String orderNumber) {
        orderService.cancelOrder(orderNumber);
        return "Order Cancelled Successfully";
    }
}
