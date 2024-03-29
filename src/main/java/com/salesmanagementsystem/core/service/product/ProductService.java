package com.salesmanagementsystem.core.service.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.ProductRepository;
import com.salesmanagementsystem.core.model.product.Product;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product createProduct(String name, String description, String category, int initialQuantity, double price) {
        Product product = new Product(name, description, category, initialQuantity, price, new Date());
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, String name, String description, String category, int quantity, double price) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setName(name);
            product.setDescription(description);
            product.setCategory(category);
            product.setInitialQuantity(quantity);
            product.setPrice(price);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Object[]> getInventoryStatus() {
        return productRepository.getInventoryStatus();
    }

    public List<Object[]> getSalesPerformance() {
        return productRepository.getSalesPerformance();
    }

    public List<Object[]> getPricingAnalysis() {
        return productRepository.getPricingAnalysis();
    }
}