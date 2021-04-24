package com.springchicken.presentation.product;

import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.dao.Order;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;
import com.springchicken.logic.CustomerDataService;
import com.springchicken.logic.OrderDataService;
import com.springchicken.presentation.product.v1.models.CustomerResponseDTO;
import com.springchicken.presentation.product.v1.models.OrdersResponseDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.CheckForNull;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for customer request mapping
 */
@RestController
@RequestMapping("/customer/v1")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class CustomerController
{
    private final CustomerDataService customerDataService;
    private final OrderDataService orderDataService;

    @Autowired
    public CustomerController(CustomerDataService customerDataService, OrderDataService orderDataService)
    {
        this.customerDataService = customerDataService;
        this.orderDataService = orderDataService;
    }

    /**
     * Returns a list of all customers
     * @return Customer List Response
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCustomers()
    {
            return ResponseEntity.ok(customerDataService.getCustomers()
                    .stream()
                    .map(customer -> new CustomerResponseDTO(customer))
                    .collect(Collectors.toList()));
    }

    /**
     * This method gets customer information based on customer ID
     * @param customerId customer's ID number
     * @return A repsonse containing customer information
     * @throws CustomerNumberNotFoundException when customer is not in database
     */
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getACustomer (
            @NotNull
            @Positive
            @PathVariable("customerId")
            final Integer customerId) throws CustomerNumberNotFoundException
    {
        Customer customer = customerDataService.getCustomerByNumber(new CustomerNumber(customerId));
        return ResponseEntity.ok(new CustomerResponseDTO(customer));
    }

    /**
     * Returns orders by customer number
     * @param customerId customer's ID number
     * @param startTime (Optional) The low inclusive bound for the order date
     * @param endTime (Optional) The upper inclusive bound for the order date
     * @return Response containing list of orders for customer
     * @throws CustomerNumberNotFoundException when customer is not found in database
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/orders/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProductsByCustomerGuid(
            @NotNull
            @Positive
            @PathVariable("customerId")
            final Integer customerId,
            @CheckForNull
            @Positive
            final Long startTime,
            @CheckForNull
            @Positive
            final Long endTime) throws CustomerNumberNotFoundException
    {
            LocalDate startTimeDate = null;
            System.out.println("Test");
            System.out.println(customerId);
            if (startTime != null)
            {
                startTimeDate = LocalDate.from(Instant.ofEpochSecond(startTime));
            }
            LocalDate endTimeDate = null;
            if (endTime != null)
            {
                endTimeDate = LocalDate.from(Instant.ofEpochSecond(endTime));
            }
            CustomerNumber customerNumber = new CustomerNumber(customerId);
            Customer customer = customerDataService.getCustomerByNumber(customerNumber);
            List<Order> orders = orderDataService.getOrders(customerNumber, Optional.ofNullable(startTimeDate),
                    Optional.ofNullable(endTimeDate));

            return ResponseEntity.ok(new OrdersResponseDTO(customer, orders));
        }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
