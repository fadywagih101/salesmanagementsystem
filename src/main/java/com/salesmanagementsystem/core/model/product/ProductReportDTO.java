package com.salesmanagementsystem.core.model.product;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductReportDTO {

    private int totalNumberOfSales;
    private List<Object[]> inventoryStatus;
    private List<Object[]> salesPerformance;
    private List<Object[]> pricingAnalysis;


    public ProductReportDTO(List<Object[]> inventoryStatus, List<Object[]> salesPerformance, List<Object[]> pricingAnalysis) {
        this.inventoryStatus = inventoryStatus;
        this.salesPerformance = salesPerformance;
        this.pricingAnalysis = pricingAnalysis;
    }   
}
