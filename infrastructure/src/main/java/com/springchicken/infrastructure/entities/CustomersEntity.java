package com.springchicken.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * JPA entity class for customers
 */
@Entity(name = "customers")
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("checkstyle:magicnumber")
public class CustomersEntity
{

    @Id
    @Column(name = "customerNumber", nullable = false)
    private int customerNumber;

    @Basic
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Basic
    @Column(name = "contactlastname", nullable = false, length = 50)
    private String contactLastName;

    @Basic
    @Column(name = "contactfirstname", nullable = false, length = 50)
    private String contactFirstName;

    @Basic
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Basic
    @Column(name = "addressline1", nullable = false, length = 50)
    private String addressLine1;

    @Basic
    @Column(name = "addressline2", nullable = true, length = 50)
    private String addressLine2;

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Basic
    @Column(name = "state", nullable = true, length = 50)
    private String state;

    @Basic
    @Column(name = "postalcode", nullable = true, length = 15)
    private String postalCode;

    @Basic
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Basic
    @Column(name = "creditlimit", nullable = true, precision = 2)
    private BigDecimal creditLimit;


}
