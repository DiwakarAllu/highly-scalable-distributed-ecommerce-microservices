package com.diwakarallu.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diwakarallu.ecommerce.order.client.InventoryClient;
import com.diwakarallu.ecommerce.order.dto.OrderRequest;
import com.diwakarallu.ecommerce.order.event.OrderPlacedEvent;
import com.diwakarallu.ecommerce.order.exception.ProductOutOfStockException;
import com.diwakarallu.ecommerce.order.model.Order;
import com.diwakarallu.ecommerce.order.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {

            // Thread.sleep(5000);
            var order = mapToOrder(orderRequest);
            orderRepository.save(order);

            // send the msg to kafka topic - order-placed
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());

            log.info("Start - Sending OrderPlacedEvent to Kafka Topic: order-placed for Order Number: {}",
                    order.getOrderNumber());
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("Completed - Sending OrderPlacedEvent to Kafka Topic: order-placed for Order Number: {}",
                    order.getOrderNumber());
        } else {
            throw new ProductOutOfStockException(
                    "Product with SKU Code " + orderRequest.skuCode() + "and quantity " + orderRequest.quantity()
                            + " is not available in inventory.");
        }
    }

    private static Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}
