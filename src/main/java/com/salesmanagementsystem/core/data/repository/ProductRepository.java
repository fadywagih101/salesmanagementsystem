package com.salesmanagementsystem.core.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesmanagementsystem.core.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p, p.quantity AS inventoryQuantity FROM Product p")
    List<Object[]> getInventoryStatus();

    @Query("SELECT t.product, SUM(t.quantity) AS totalQuantity FROM Transaction t GROUP BY t.product ORDER BY totalQuantity DESC")
    List<Object[]> getSalesPerformance();

    @Query("SELECT p, AVG(p.price) AS averagePrice, MIN(p.price) AS minPrice, MAX(p.price) AS maxPrice FROM Product p")
    List<Object[]> getPricingAnalysis();
}
