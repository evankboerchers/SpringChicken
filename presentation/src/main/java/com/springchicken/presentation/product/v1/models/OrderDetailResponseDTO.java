package com.springchicken.presentation.product.v1.models;



import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.springchicken.logic.dao.OrderEntry;

/**
 * This is the DTO for the order detail
 */
public class OrderDetailResponseDTO
{
    private Integer quantity;
    private ProductResponseDTO product;

    private OrderDetailResponseDTO()
    {
        quantity = 0;
        product = ProductResponseDTO.EMPTY;
    }

    public OrderDetailResponseDTO(OrderEntry orderEntry)
    {
        quantity = orderEntry.quantity;
        product = new ProductResponseDTO(orderEntry.product);
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public ProductResponseDTO getProduct()
    {
        return product;
    }

    public void setProduct(ProductResponseDTO product)
    {
        this.product = product;
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(Object o)
    {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
