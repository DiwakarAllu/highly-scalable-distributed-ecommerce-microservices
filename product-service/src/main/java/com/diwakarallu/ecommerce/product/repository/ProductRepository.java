package com.diwakarallu.ecommerce.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diwakarallu.ecommerce.product.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    /**
     * MongoRepository provides all the basic operations:
     * 
     * save(Product product) → insert or update returns the saved product
     * 
     * findAll() → get List of all products
     * 
     * findById(String id) → get a Optional product by ID
     * 
     * delete(Product product) → delete a product
     * 
     * deleteById(String id) → delete by ID
     */

    // Find products within a price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Find products by name or description containing keyword (case-insensitive)
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    // Find product by SKU code
    Optional<Product> findBySkuCode(String skuCode);
}
