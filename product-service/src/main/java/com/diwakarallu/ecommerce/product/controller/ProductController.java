package com.diwakarallu.ecommerce.product.controller;

import com.diwakarallu.ecommerce.product.dto.ProductRequest;
import com.diwakarallu.ecommerce.product.dto.ProductResponse;
import com.diwakarallu.ecommerce.product.exception.ResourceNotFoundException;
import com.diwakarallu.ecommerce.product.mapper.ProductMapper;
import com.diwakarallu.ecommerce.product.model.Product;
import com.diwakarallu.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        Product saved = productService.createProduct(product);
        ProductResponse response = ProductMapper.toResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response = productService.getAllProducts()
                .stream()
                .map(product -> ProductMapper.toResponse(product))
                .collect(Collectors.toList());
        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id " + id));
        ProductResponse response = ProductMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request) {
        Product updatedEntity = ProductMapper.toEntity(request);
        Product updated = productService.updateProduct(id, updatedEntity);
        ProductResponse response = ProductMapper.toResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<ProductResponse>> getProductsByPriceRange(
            @RequestParam java.math.BigDecimal min,
            @RequestParam java.math.BigDecimal max) {

        List<ProductResponse> response = productService.getProductsByPriceRange(min, max)
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        List<ProductResponse> response = productService.searchProducts(keyword)
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sku/{skuCode}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable String skuCode) {
        Product product = productService.getProductBySku(skuCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU " + skuCode));
        return ResponseEntity.ok(ProductMapper.toResponse(product));
    }
}
