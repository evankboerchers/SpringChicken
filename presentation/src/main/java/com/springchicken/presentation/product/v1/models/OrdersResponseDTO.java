package com.springchicken.presentation.product.v1.models;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.Order;

/**
 * The object representing the response for many orders
 */
public class OrdersResponseDTO
{
    private CustomerResponseDTO customer;
    private List<SingleOrderResponseDTO> orders;

    private OrdersResponseDTO()
    {
        customer = CustomerResponseDTO.EMPTY;
        orders = Collections.emptyList();
    }

    public OrdersResponseDTO(Customer customerDetails, Collection<Order> orders)
    {
        this.customer = new CustomerResponseDTO(customerDetails);
        this.orders = orders.stream().map(order -> new SingleOrderResponseDTO(order))
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerResponseDTO customer)
    {
        this.customer = customer;
    }

    public List<SingleOrderResponseDTO> getOrders()
    {
        return orders;
    }

    public void setOrders(List<SingleOrderResponseDTO> orders)
    {
        this.orders = orders;
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

