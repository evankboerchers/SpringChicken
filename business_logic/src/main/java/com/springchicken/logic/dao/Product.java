package com.springchicken.logic.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * The details about a single product
 */
@SuppressFBWarnings(value = { "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD" }, justification = "This is just a record")
public class Product implements Serializable
{
    private static final long serialVersionUID = 4L;

    // CSOFF: VisibilityModifier
    public final String productName;
    public final String productDescription;
    // CSON: VisibilityModifier

    public Product(final String productName, final String productDescription)
    {
        this.productName = productName;
        this.productDescription = productDescription;
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
