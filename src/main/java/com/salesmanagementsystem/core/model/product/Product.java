package com.salesmanagementsystem.core.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Product(String name, String description, String category, int initialQuantity, double price, Date creationDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.initialQuantity = initialQuantity;
        this.price = price;
        this.creationDate = new Date();
    }   
}
