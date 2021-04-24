package com.springchicken.logic.dao;

import com.springchicken.logic.dao.exceptions.MissingAddressException;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * This class provides a representation for a single customer
 */
@SuppressFBWarnings(value = { "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD" }, justification = "This is just a record")
public class Customer implements Serializable
{
    private static final long serialVersionUID = 4L;

    // CSOFF: VisibilityModifier
    public final CustomerNumber customerNumber;
    public final String firstName;
    public final String lastName;
    public final String phoneNumber;
    public final Address address;
    // CSON: VisibilityModifier

    public Customer(CustomerNumber customerNumber, String firstName, String lastName, String phoneNumber, Address address)
    {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    static class CustomerBuilder
    {
        private CustomerNumber customerNumber;
        private String firstName = StringUtils.EMPTY;
        private String lastName = StringUtils.EMPTY;
        private String phoneNumber = StringUtils.EMPTY;
        @CheckForNull
        private Address address;

        CustomerBuilder(final CustomerNumber customerNumber)
        {
            this.customerNumber = customerNumber;
        }

        public Customer build() throws MissingAddressException
        {
            if (null == address)
            {
                throw new MissingAddressException("Attempt to build Customer without address. customerNumber: " + customerNumber);
            }
            return new Customer(customerNumber, firstName, lastName, phoneNumber, address);
        }

        public void firstName(final String firstName)
        {
           this.firstName = firstName;
        }

        public void lastName(final String lastName)
        {
            this.lastName = lastName;
        }

        public void phoneNumber(final String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

        public void address(final Address address)
        {
            this.address = address;
        }
    }

    public String getFirstName()
    {
        return this.firstName;
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
