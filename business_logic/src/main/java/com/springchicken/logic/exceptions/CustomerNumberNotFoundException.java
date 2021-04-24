package com.springchicken.logic.exceptions;

/**
 * This exception is to be thrown when a function call is made that includes a customer number that does not exist
 * in the backing data
 */
public class CustomerNumberNotFoundException extends Exception
{
    public CustomerNumberNotFoundException(String message)
    {
        super(message);
    }

    public CustomerNumberNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CustomerNumberNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
