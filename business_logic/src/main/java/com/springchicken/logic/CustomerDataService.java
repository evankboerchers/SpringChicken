package com.springchicken.logic;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;

import java.util.List;

/**
 * This class provides an interface that is implemented concretely to get information about
 * customers. The details of how that happens are abstracted by the implementing class.
 */

public interface CustomerDataService
{
    /**
     * Get a list of customers
     * @return A list of all known customers
     */
    List<Customer> getCustomers();

    /**
     * Get the information for a single customer based on their number
     * @param customerNumber The number of the customer
     * @return The details for that customer
     * @throws CustomerNumberNotFoundException If the customer number is not found, this exception is thrown
     */
    Customer getCustomerByNumber(CustomerNumber customerNumber) throws CustomerNumberNotFoundException;
}