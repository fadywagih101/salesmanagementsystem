package com.salesmanagementsystem.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Object[]> getInventoryStatus() {
        return productRepository.getInventoryStatus();
    }

    public List<Object[]> getSalesPerformance() {
        return productRepository.getSalesPerformance();
    }

    public List<Object[]> getPricingAnalysis() {
        return productRepository.getPricingAnalysis();
    }

    // Add additional methods if needed
}