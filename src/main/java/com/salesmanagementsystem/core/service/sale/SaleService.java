package com.salesmanagementsystem.core.service.sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.SaleRepository;
import com.salesmanagementsystem.core.data.repository.ClientRepository;
import com.salesmanagementsystem.core.data.repository.UserRepository;
import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.product.Product;
import com.salesmanagementsystem.core.model.sale.Sale;
import com.salesmanagementsystem.core.model.sale.SaleRequestDTO;
import com.salesmanagementsystem.core.model.transaction.Transaction;
import com.salesmanagementsystem.core.model.user.User;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }

    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }

    public int getTotalNumberOfSales(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTotalNumberOfSales(startDate, endDate);
    }

    public double getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTotalRevenue(startDate, endDate);
    }

    public List<Object[]> getTopSellingProducts(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTopSellingProducts(startDate, endDate);
    }

    public List<Object[]> getTopPerformingSellers(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getTopPerformingSellers(startDate, endDate);
    }
}
