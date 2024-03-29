package com.salesmanagementsystem.core.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.sale.Sale;
import com.salesmanagementsystem.core.model.sale.SaleReportDTO;
import com.salesmanagementsystem.core.model.sale.SaleRequestDTO;
import com.salesmanagementsystem.core.model.user.User;
import com.salesmanagementsystem.core.service.sale.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{saleId}")
    public Sale getSaleById(@PathVariable Long saleId) {
        return saleService.getSaleById(saleId);
    }

    @DeleteMapping("/{saleId}")
    public void deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
    }

    @GetMapping("/statistics")
    public SaleReportDTO getSaleReport(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int totalNumberOfSales = saleService.getTotalNumberOfSales(startDate, endDate);
        double totalRevenue = saleService.getTotalRevenue(startDate, endDate);
        List<Object[]> topSellingProducts = saleService.getTopSellingProducts(startDate, endDate);
        List<Object[]> topPerformingSellers = saleService.getTopPerformingSellers(startDate, endDate);
        // Convert the data to DTO
        return new SaleReportDTO(totalNumberOfSales, totalRevenue, topSellingProducts, topPerformingSellers);
    }
}