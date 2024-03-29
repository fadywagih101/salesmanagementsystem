package com.salesmanagementsystem.core.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.salesmanagementsystem.core.model.SalesManagementObject;
import com.salesmanagementsystem.core.model.product.Product;
import com.salesmanagementsystem.core.model.sale.Sale;

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
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;
    
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
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

    public Transaction() {
    }
}
