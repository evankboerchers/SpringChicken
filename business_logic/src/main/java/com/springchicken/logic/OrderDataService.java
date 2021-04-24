package com.springchicken.logic;

import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.dao.Order;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * A service to get orders for customers
 */

public interface OrderDataService
{
    /**
     * Get the orders for each of the customers provided
     * @param customerNumber The customer number to look up
     * @param startDate An optional inclusive start date to use to bound the request
     * @param endDate An optional inclusive end date to use to bound the request
     * @return A map that references the customer number to their orders
     * @throws CustomerNumberNotFoundException If the provided customer number is not found
     */
    List<Order> getOrders(CustomerNumber customerNumber, Optional<LocalDate> startDate, Optional<LocalDate> endDate) throws
            CustomerNumberNotFoundException;
}
