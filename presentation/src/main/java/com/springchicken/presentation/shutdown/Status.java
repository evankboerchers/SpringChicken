package com.springchicken.presentation.shutdown;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Component;

/**
 * Shutdown status class
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
@Component
public class Status
{

    public Boolean isShutdown = Boolean.FALSE;

    public synchronized Boolean getIsShutdown()
    {
        return isShutdown;
    }

    public synchronized void setIsShutdown(Boolean shutdown)
    {
        isShutdown = shutdown;
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
