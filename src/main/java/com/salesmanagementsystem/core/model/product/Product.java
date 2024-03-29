package com.salesmanagementsystem.core.model.product;

import org.hibernate.envers.Audited;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.salesmanagementsystem.core.model.SalesManagementObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Audited
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
@ToString
public class Product extends SalesManagementObject {

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    private int initialQuantity;
    
    @Column(nullable = false)
    private double price;
    
    @Column(nullable = false)
    private Date creationDate;

    public Product(String name, String description, String category, int initialQuantity, double price, Date creationDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.initialQuantity = initialQuantity;
        this.price = price;
        this.creationDate = new Date();
    }   
}
