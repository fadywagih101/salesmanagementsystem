package com.salesmanagementsystem.core.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.salesmanagementsystem.core.model.SalesManagementObject;
import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.product.Product;
import com.salesmanagementsystem.core.model.sale.Sale;
import com.salesmanagementsystem.core.model.user.User;

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
public class Transaction extends SalesManagementObject {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private double price;

    public Transaction(Product product, Sale sale, int quantity, double price) {
        this.product = product;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
    }
}
