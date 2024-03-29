package com.salesmanagementsystem.core.model.sale;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SaleReportDTO {

    private int totalNumberOfSales;
    private double totalRevenue;
    private List<Object[]> topSellingProducts;
    private List<Object[]> topPerformingSellers;

    public SaleReportDTO(int totalNumberOfSales, double totalRevenue, List<Object[]> topSellingProducts, List<Object[]> topPerformingSellers) {
        this.totalNumberOfSales = totalNumberOfSales;
        this.totalRevenue = totalRevenue;
        this.topSellingProducts = topSellingProducts;
        this.topPerformingSellers = topPerformingSellers;
    }   
}
