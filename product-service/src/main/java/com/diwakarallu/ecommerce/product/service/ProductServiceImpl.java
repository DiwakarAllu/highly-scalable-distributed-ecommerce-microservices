package com.diwakarallu.ecommerce.product.service;

import com.diwakarallu.ecommerce.product.exception.ResourceNotFoundException;
import com.diwakarallu.ecommerce.product.model.Product;
import com.diwakarallu.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Service ideally deals with entities,and controller maps to DTOs.
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

        private final ProductRepository productRepository;

        @Override
        public Product createProduct(Product product) {
                return productRepository.save(product);
        }

        @Override
        public List<Product> getAllProducts() {
                return productRepository.findAll();
        }

        @Override
        public Optional<Product> getProductById(String id) {
                return productRepository.findById(id);
        }

        @Override
        public void deleteProduct(String id) {
                if (!productRepository.existsById(id)) {
                        throw new ResourceNotFoundException("Product not found with id " + id);
                }
                productRepository.deleteById(id);
        }

        @Override
        public Product updateProduct(String id, Product updatedProduct) {
                return productRepository.findById(id)
                                .map(existing -> {
                                        existing.setName(updatedProduct.getName());
                                        existing.setDescription(updatedProduct.getDescription());
                                        existing.setSkuCode(updatedProduct.getSkuCode());
                                        existing.setPrice(updatedProduct.getPrice());
                                        return productRepository.save(existing);
                                })
                                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        }

        @Override
        public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
                return productRepository.findByPriceBetween(minPrice, maxPrice);
        }

        @Override
        public List<Product> searchProducts(String keyword) {
                return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,
                                keyword);
        }

        @Override
        public Optional<Product> getProductBySku(String skuCode) {
                return productRepository.findBySkuCode(skuCode);
        }
}
