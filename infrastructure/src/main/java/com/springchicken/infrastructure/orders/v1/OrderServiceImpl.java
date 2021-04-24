package com.springchicken.infrastructure.orders.v1;

import com.springchicken.infrastructure.customer.v1.CustomerServiceImpl;
import com.springchicken.infrastructure.entities.OrdersEntity;
import com.springchicken.infrastructure.repos.OrdersRepository;
import com.springchicken.logic.OrderDataService;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.dao.Product;
import com.springchicken.logic.dao.Order;
import com.springchicken.logic.dao.OrderEntry;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service("OrderServiceV1")
@CacheConfig(cacheNames = "orders")
public class OrderServiceImpl implements OrderDataService
{
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrdersRepository ordersRepo;

    private CustomerServiceImpl customerService;

    @Autowired
    public OrderServiceImpl (OrdersRepository ordersRepo, CustomerServiceImpl customerService)
    {
        this.ordersRepo = ordersRepo;
        this.customerService = customerService;
    }

    @Override
    @Cacheable(key = "#customerNumber.number")
    public List<Order> getOrders(CustomerNumber customerNumber, Optional<LocalDate> startDateOptional,
            Optional<LocalDate> endDateOptional) throws CustomerNumberNotFoundException
    {

        Customer customer = customerService.getCustomerByNumber(customerNumber);

        return ordersRepo.getOrdersByCustomerNumber(customerNumber.number)
                .stream().map(ordersEntity -> createOrder(ordersEntity, customer)).collect(Collectors.toList());

    }

    /**
     * Helper method for creating an order dao from an orderEntity and customer
     * @param orderEntity The order entity for given customer
     * @param customer The customer dao
     * @return The order object for given customer
     */
    private Order createOrder(OrdersEntity orderEntity, Customer customer)
    {
        List<OrderEntry> orderEntries = orderEntity.getOrderdetailsByOrderNumber().stream().map(od ->
                new OrderEntry(od.getQuantityOrdered(),
                        new Product(od.getProductsByProductCode().getProductName(),
                                od.getProductsByProductCode().getProductDescription()))).collect(Collectors.toList());
        return new Order(customer, orderEntity.getOrderDate().toLocalDate(), orderEntries);
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

}
