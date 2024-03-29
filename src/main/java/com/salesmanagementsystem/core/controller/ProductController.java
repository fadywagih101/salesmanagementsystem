package com.salesmanagementsystem.core.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        String name = productRequestDTO.getName();
        String description = productRequestDTO.getDescription();
        String category = productRequestDTO.getCategory();
        int initialQuantity = productRequestDTO.getQuantity();
        double price = productRequestDTO.getPrice();
        Product createdProduct = productService.createProduct(name, description, category, initialQuantity, price);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) {
        String name = productRequestDTO.getName();
        String description = productRequestDTO.getDescription();
        String category = productRequestDTO.getCategory();
        int quantity = productRequestDTO.getQuantity();
        double price = productRequestDTO.getPrice();
        Product updatedProduct = productService.updateProduct(productId, name, description, category, quantity, price);
        logger.info("updaed product is: {}", updatedProduct);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/report")
    public ResponseEntity<ProductReportDTO> getProductReport() {
        List<Object[]> inventoryStatus = productService.getInventoryStatus();
        List<Object[]> salesPerformance = productService.getSalesPerformance();
        List<Object[]> pricingAnalysis = productService.getPricingAnalysis();
        // Convert the data to DTO
        ProductReportDTO productReportDTO = new ProductReportDTO(inventoryStatus, salesPerformance, pricingAnalysis);
        return new ResponseEntity<>(productReportDTO, HttpStatus.OK);
    }

}

