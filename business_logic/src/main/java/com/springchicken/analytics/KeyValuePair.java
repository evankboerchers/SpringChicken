package com.springchicken.analytics;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.CheckForNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * The class represents a String-Object pair.<br/>
 * Implementation of {@code toString()} delays conversion of the Object to a string.
 */
public class KeyValuePair
{
    private static final Logger logger = LoggerFactory.getLogger(KeyValuePair.class);
    private static final String NULL_STRING = "null";
    private static final String QUOTE = "\"";
    private static final String QUOTE_ESC = "[\\quote]";

    private final String key;
    private final Supplier<String> supplier;

    /**
     * Creates a pair with string value.<br/>
     * " and \n characters will be replaced by "[\quote]" and "[\newline]"
     *
     * @param key The key to log with
     * @param value The value to log
     */
    public KeyValuePair(final String key, @NonNull final String value)
    {
        this.key = key;
        this.supplier = () -> formatString(value);
    }

    /**
     * Creates a pair with Date value.<br/>
     * Output string will be formatted with "yyyy-MM-dd\'T\'HH:mm:ssz"
     *
     * @param key The key to log with
     * @param value The value to log
     */
    public KeyValuePair(final String key, @CheckForNull final Date value)
    {
        this.key = key;
        this.supplier = () -> formatDate(value);
    }

    /**
     * Creates a pair with arbitrary object that should have implementation of {@code toString()}.
     *
     * @param key The key to log with
     * @param value The value to log
     */
    public KeyValuePair(final String key, @CheckForNull final Object value)
    {
        this.key = key;
        this.supplier = () -> formatObject(value);
    }

    private static String formatString(@CheckForNull final String value)
    {
        if (value == null)
        {
            logger.warn("Passed string value is null");
            return NULL_STRING;
        }
        return value
                .replace(QUOTE, QUOTE_ESC);
    }

    private static String formatDate(@CheckForNull final Date value)
    {
        if (value == null)
        {
            logger.warn("Passed date value is null");
            return NULL_STRING;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssz");
        return dateFormat.format(value);
    }

    private static String formatObject(@CheckForNull final Object value)
    {
        if (value == null)
        {
            logger.warn("Passed object is null");
            return NULL_STRING;
        }
        return formatString(value.toString());
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

    @Override
    public String toString()
    {
        return key + '=' + QUOTE + this.supplier.get() + QUOTE;
    }
}