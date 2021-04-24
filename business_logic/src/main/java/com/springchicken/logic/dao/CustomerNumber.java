package com.springchicken.logic.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * This class encapsulates a CustomerNumber so that it can be validated to ensure it is greater than zero
 */
@SuppressFBWarnings(value = { "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD" }, justification = "This is just a record")
public class CustomerNumber implements Serializable
{
    private static final long serialVersionUID = 4L;

    // CSOFF: VisibilityModifier
    public final Integer number;
    // CSON: VisibilityModifier

    public CustomerNumber(final Integer number)
    {
        this.number = number;
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
