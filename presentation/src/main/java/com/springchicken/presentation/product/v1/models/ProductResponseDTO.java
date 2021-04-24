package com.springchicken.presentation.product.v1.models;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.springchicken.logic.dao.Product;

/**
 * DTO to define a response regarding a Product
 */
public class ProductResponseDTO
{
    public static final ProductResponseDTO EMPTY = new ProductResponseDTO();

    private String productName;
    @JsonProperty("productDescription")
    private String productDesc;

    private ProductResponseDTO()
    {
        productName = StringUtils.EMPTY;
        productDesc = StringUtils.EMPTY;
    }

    public ProductResponseDTO(Product product)
    {
        productName = product.productName;
        productDesc = product.productDescription;
    }


    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductDesc()
    {
        return productDesc;
    }

    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }

    /**
     * Entity equals method
     *
     * @param o other object to compare with
     *
     * @return true if this entity equals the other
     */
    @Override
    public boolean equals(Object o)
    {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * Entity hashcode method
     *
     * @return Hash of the unique identifier
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Entity toString method
     *
     * @return String representation of this entity
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}

