package com.springchicken.presentation.product.v1.models;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.springchicken.logic.dao.Customer;


/**
 * A class for basic personal contact info
 */
public class Contact
{
    public static final Contact EMPTY = new Contact();

    String contactLastName;
    String contactFirstName;
    String phoneNumber;

    private Contact()
    {
        contactLastName = StringUtils.EMPTY;
        contactFirstName = StringUtils.EMPTY;
        phoneNumber = StringUtils.EMPTY;
    }

    public Contact(Customer customer)
    {
        contactLastName = customer.lastName;
        contactFirstName = customer.firstName;
        phoneNumber = customer.phoneNumber;
    }

    public String getContactLastName()

    {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName)
    {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName()
    {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName)
    {
        this.contactFirstName = contactFirstName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
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
