package com.springchicken.logic.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * This class provides a representation of a single order
 */
@SuppressFBWarnings(value = { "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD" }, justification = "This is just a record")
public class Order implements Serializable
{
    private static final long serialVersionUID = 4L;

    // CSOFF: VisibilityModifier
    public final Customer customer;
    public final LocalDate orderDate;
    public final List<OrderEntry> orders;
    // CSON: VisibilityModifier

    public Order(final Customer customer, final LocalDate orderDate, final List<OrderEntry> orders)
    {
        this.customer = customer;
        this.orderDate = orderDate;
        this.orders = Collections.unmodifiableList(orders);
    }

    static class OrderBuilder
    {
        private Customer customer;
        private LocalDate orderDate;
        private final List<OrderEntry> orders = new ArrayList<OrderEntry>();

        OrderBuilder(final Customer customer, final LocalDate orderDate)
        {
            this.customer = customer;
            this.orderDate = orderDate;
        }

        public Order build()
        {
            return new Order(customer, orderDate, orders);
        }

        public void addOrderEntry(final OrderEntry entry)
        {
            this.orders.add(entry);
        }
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
