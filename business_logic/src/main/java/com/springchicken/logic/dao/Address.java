package com.springchicken.logic.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * This class defines a basic address
 */
@SuppressFBWarnings(value = { "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD" }, justification = "This is just a record")
public class Address implements Serializable
{
    private static final long serialVersionUID = 4L;

    // CSOFF: VisibilityModifier
    public final String streetAddress;
    public final String city;
    public final String state;
    public final String country;
    public final String postalCode;
    // CSON: VisibilityModifier

    public Address(final String streetAddress, final String city, final String state, final String country,
            final String postalCode)
    {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
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
