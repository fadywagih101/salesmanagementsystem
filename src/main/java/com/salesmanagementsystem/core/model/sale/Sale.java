package com.salesmanagementsystem.core.model.sale;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.salesmanagementsystem.core.model.SalesManagementObject;
import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.user.User;
import com.salesmanagementsystem.core.model.transaction.Transaction;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
@ToString
public class Sale extends SalesManagementObject {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    
    @Column(nullable = false)
    private double total;
    
    @CreationTimestamp
    private LocalDateTime creationDate;

    public Sale() {
        // Default constructor
    }

    public Sale(Client client, User seller, double total, LocalDateTime creationDate) {
        this.client = client;
        this.seller = seller;
        this.total = total;
        this.creationDate = creationDate;
    }
}
