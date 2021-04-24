package com.springchicken.logic.dao.exceptions;

/**
 * This exception is to be thrown when an invalid customer number is provided
 */
public class InvalidCustomerNumberException extends Exception
{
    public InvalidCustomerNumberException(String message)
    {
        super(message);
    }
}
