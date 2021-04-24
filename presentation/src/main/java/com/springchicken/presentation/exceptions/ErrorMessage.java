package com.springchicken.presentation.exceptions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Custom template for Error message responses
 */
public class ErrorMessage
{
    private int status;
    private String timestamp;
    private String message;
    private String error;
    private String path;

    public ErrorMessage(String timestamp, int status, String error, String message, String path)
    {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
        this.path = path;
    }

    public int getStatus()
    {
        return status;
    }

    public String getError()
    {
        return error;
    }

    public String getMessage()
    {
        return message;
    }

    public String getPath()
    {
        return path;
    }

    public String getTimestamp()
    {
        return timestamp;
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
