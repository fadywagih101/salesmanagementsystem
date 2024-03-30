package com.salesmanagementsystem.core.model.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.salesmanagementsystem.core.model.SalesManagementObject;

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
public class Client extends SalesManagementObject {

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String mobile;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String address;

    public Client() {
        // Default constructor
    }

    public Client(String name, String lastName, String mobile, String email, String address) {
        this.name = name;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }
}
