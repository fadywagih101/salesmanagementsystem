package com.salesmanagementsystem.core.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesmanagementsystem.core.model.client.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT COUNT(c) FROM Client c")
    int getTotalNumberOfClients();

    @Query("SELECT c, SUM(t.product.price * t.quantity) AS totalSpending FROM Client c JOIN c.sales s JOIN s.transactions t GROUP BY c ORDER BY totalSpending DESC")
    List<Object[]> getTopSpendingClients();

    // Add additional custom queries for client activity and client location statistics if needed
}