package com.salesmanagementsystem.core.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesmanagementsystem.core.model.sale.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT COUNT(s) FROM Sale s WHERE s.date BETWEEN :startDate AND :endDate")
    int getTotalNumberOfSales(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(t.product.price * t.quantity) FROM Sale s JOIN s.transactions t WHERE s.date BETWEEN :startDate AND :endDate")
    double getTotalRevenue(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t.product, SUM(t.quantity) AS totalQuantity FROM Sale s JOIN s.transactions t WHERE s.date BETWEEN :startDate AND :endDate GROUP BY t.product ORDER BY totalQuantity DESC")
    List<Object[]> getTopSellingProducts(LocalDate startDate, LocalDate endDate);

    @Query("SELECT s.seller, COUNT(s) AS saleCount FROM Sale s WHERE s.date BETWEEN :startDate AND :endDate GROUP BY s.seller ORDER BY saleCount DESC")
    List<Object[]> getTopPerformingSellers(LocalDate startDate, LocalDate endDate);
}
