package com.springchicken.infrastructure.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Primary key for the jpa entity class order details
 */
@Data
@SuppressWarnings("checkstyle:magicnumber")
public class OrderdetailsEntityPK implements Serializable
{
    private static final long serialVersionUID = 4L;

    @Column(name = "orderNumber", nullable = false)
    @Id
    private int orderNumber;

    @Column(name = "productCode", nullable = false, length = 15)
    @Id
    private String productCode;
}
