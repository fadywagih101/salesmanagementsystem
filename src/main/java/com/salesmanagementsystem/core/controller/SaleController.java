package com.salesmanagementsystem.core.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanagementsystem.core.model.sale.Sale;
import com.salesmanagementsystem.core.model.sale.SaleReportDTO;
import com.salesmanagementsystem.core.model.sale.SaleRequestDTO;
import com.salesmanagementsystem.core.service.sale.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private static final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long saleId) {
        Sale sale = saleService.getSaleById(saleId);
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDTO saleRequest) {
        Sale sale = saleService.createSale(saleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<SaleReportDTO> getSaleReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        int totalNumberOfSales = saleService.getTotalNumberOfSales(startDate, endDate);
        double totalRevenue = saleService.getTotalRevenue(startDate, endDate);
        List<Object[]> topSellingProducts = saleService.getTopSellingProducts(startDate, endDate);
        List<Object[]> topPerformingSellers = saleService.getTopPerformingSellers(startDate, endDate);
        logger.info("top performer are: {}", topPerformingSellers);
        SaleReportDTO saleReportDTO = new SaleReportDTO(totalNumberOfSales, totalRevenue, topSellingProducts, topPerformingSellers);
        return ResponseEntity.ok(saleReportDTO);
    }

}