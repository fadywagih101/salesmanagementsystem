package com.salesmanagementsystem.core.model.sale;


import java.util.Date;
import java.util.List;

import com.salesmanagementsystem.core.model.client.ClientRequestDTO;
import com.salesmanagementsystem.core.model.transaction.TransactionRequestDTO;
import com.salesmanagementsystem.core.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaleRequestDTO {

    private Date creationDate;
    private ClientRequestDTO client;
    private User seller;
    private double total;
    private List<TransactionRequestDTO> transactions;
}
