package com.diwakarallu.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diwakarallu.ecommerce.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
