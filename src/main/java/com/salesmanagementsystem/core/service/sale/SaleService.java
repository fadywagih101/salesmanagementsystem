package com.salesmanagementsystem.core.service.sale;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

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
