package com.springchicken.infrastructure.entities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * JPA entity class for orders
 */
@Entity
@Table(name = "orders", schema = "classicmodels", catalog = "")
@Data
@SuppressWarnings("checkstyle:magicnumber")
@SuppressFBWarnings(value={"EI_EXPOSE_REP", "EI_EXPOSE_REP2"},
        justification="Is JPA Entity")
public class OrdersEntity
{

    @Id
    @Column(name = "orderNumber", nullable = false)
    private int orderNumber;

    @Basic
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Basic
    @Column(name = "customerNumber", nullable = false)
    private int customerNumber;

    @Basic
    @Column(name = "requiredDate", nullable = false)
    private Date requiredDate;

    @Basic
    @Column(name = "shippedDate", nullable = true)
    private Date shippedDate;

    @Basic
    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Basic
    @Column(name = "comments", nullable = true, columnDefinition = "text")
    private String comments;

    @OneToMany(mappedBy = "ordersByOrderNumber")
    private Collection<OrderdetailsEntity> orderdetailsByOrderNumber;

}
