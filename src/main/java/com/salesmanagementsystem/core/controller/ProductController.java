package com.salesmanagementsystem.core.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesmanagementsystem.core.model.product.Product;
import com.salesmanagementsystem.core.model.product.ProductReportDTO;
import com.salesmanagementsystem.core.model.product.ProductRequestDTO;
import com.salesmanagementsystem.core.service.product.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        String name = productRequestDTO.getName();
        String description = productRequestDTO.getDescription();
        String category = productRequestDTO.getCategory();
        int initialQuantity = productRequestDTO.getQuantity();
        double price = productRequestDTO.getPrice();
        return productService.createProduct(name, description, category, initialQuantity, price);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) {
        String name = productRequestDTO.getName();
        String description = productRequestDTO.getDescription();
        String category = productRequestDTO.getCategory();
        int quantity = productRequestDTO.getQuantity();
        double price = productRequestDTO.getPrice();
        return productService.updateProduct(productId, name, description, category, quantity, price);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/statistics")
    public ProductReportDTO getProductReport() {
        List<Object[]> inventoryStatus = productService.getInventoryStatus();
        List<Object[]> salesPerformance = productService.getSalesPerformance();
        List<Object[]> pricingAnalysis = productService.getPricingAnalysis();
        // Convert the data to DTO
        return new ProductReportDTO(inventoryStatus, salesPerformance, pricingAnalysis);
    }
}

