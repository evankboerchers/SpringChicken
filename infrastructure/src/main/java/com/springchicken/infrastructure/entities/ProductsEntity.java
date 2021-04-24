package com.springchicken.infrastructure.entities;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * JPA entity class for products
 */
@Entity
@Table(name = "products", schema = "classicmodels", catalog = "")
@Data
@SuppressWarnings("checkstyle:magicnumber")
public class ProductsEntity
{

    @Id
    @Column(name = "productCode", nullable = false, length = 15)
    private String productCode;

    @Basic
    @Column(name = "productName", nullable = false, length = 70)
    private String productName;

    @Basic
    @Column(name = "productScale", nullable = false, length = 10)
    private String productScale;

    @Basic
    @Column(name = "productVendor", nullable = false, length = 50)
    private String productVendor;

    @Basic
    @Column(name = "productDescription", nullable = false, columnDefinition = "text")
    private String productDescription;

    @Basic
    @Column(name = "quantityInStock", nullable = false)
    private short quantityInStock;

    @Basic
    @Column(name = "buyPrice", nullable = false, precision = 2)
    private BigDecimal buyPrice;

    @Basic
    @Column(name = "MSRP", nullable = false, precision = 2)
    private BigDecimal msrp;

    @OneToMany(mappedBy = "productsByProductCode")
    private Collection<OrderdetailsEntity> orderdetailsByProductCode;
}
