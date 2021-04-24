package com.springchicken.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * JPA entity class for order details
 */
@Entity
@Table(name = "orderdetails", schema = "classicmodels", catalog = "")
@Data
@IdClass(OrderdetailsEntityPK.class)
@SuppressWarnings("checkstyle:magicnumber")
public class OrderdetailsEntity
{

    @Id
    @Column(name = "orderNumber", nullable = false)
    private int orderNumber;

    @Id
    @Column(name = "productCode", nullable = false, length = 15)
    private String productCode;

    @Basic
    @Column(name = "quantityOrdered", nullable = false)
    private int quantityOrdered;

    @Basic
    @Column(name = "priceEach", nullable = false, precision = 2)
    private BigDecimal priceEach;

    @Basic
    @Column(name = "orderLineNumber", nullable = false)
    private short orderLineNumber;

    @ManyToOne
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber", nullable = false, insertable = false, updatable = false)
    private OrdersEntity ordersByOrderNumber;

    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "productCode", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsByProductCode;
}
