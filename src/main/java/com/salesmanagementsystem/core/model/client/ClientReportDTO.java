package com.salesmanagementsystem.core.model.client;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientReportDTO {

    private int totalNumberOfClients;
    private List<Object[]> topSpendingClients;

    public ClientReportDTO(int totalNumberOfClients, List<Object[]> topSpendingClients) {
        this.totalNumberOfClients = totalNumberOfClients;
        this.topSpendingClients = topSpendingClients;
    }  
}
