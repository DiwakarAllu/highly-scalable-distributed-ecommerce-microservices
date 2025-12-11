package com.diwakarallu.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "${inventory.url}", path = "/api/inventory" // like requestMapping
)
public interface InventoryClient {

        @GetMapping()
        boolean isInStock(
                        @RequestParam String skuCode,
                        @RequestParam Integer quantity);
}
