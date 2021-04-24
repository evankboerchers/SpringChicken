package com.springchicken.presentation.product.v1.models;



import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.springchicken.logic.dao.Order;

/**
 * DTO class for information about an Order in a response
 */
public class SingleOrderResponseDTO
{
    private List<OrderDetailResponseDTO> orderDetails;
    private Date orderDate;

    private SingleOrderResponseDTO()
    {
        orderDetails = Collections.emptyList();
        orderDate = new Date();
    }

    public SingleOrderResponseDTO(Order order)
    {
        orderDate = Date.from(order.orderDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        orderDetails = order.orders.stream()
                .map(orderEntry -> new OrderDetailResponseDTO(orderEntry))
                .collect(Collectors.toList());
    }

    public List<OrderDetailResponseDTO> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponseDTO> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public Date getOrderDate()
    {
        return (Date)orderDate.clone();
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = (Date)orderDate.clone();
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
