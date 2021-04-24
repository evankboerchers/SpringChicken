package com.springchicken.logic.dao.exceptions;

/**
 * Exception to be thrown if the address is required but not provided
 */
public class MissingAddressException extends Exception
{
    public MissingAddressException(String message)
    {
        super(message);
    }
}
