package com.diwakarallu.ecommerce.product.service;

import com.diwakarallu.ecommerce.product.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Service ideally deals with entities, and controller maps to DTOs.
public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(String id);

    void deleteProduct(String id);

    Product updateProduct(String id, Product product);

    // Advanced APIs
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> searchProducts(String keyword);

    Optional<Product> getProductBySku(String skuCode);
}
