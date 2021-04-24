package com.springchicken.infrastructure.customer.v1;

import com.springchicken.infrastructure.entities.CustomersEntity;
import com.springchicken.infrastructure.repos.CustomersRepository;
import com.springchicken.logic.CustomerDataService;
import com.springchicken.logic.dao.Address;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * {@inheritDoc}
 */
@Service("CustomerServiceV1")
@CacheConfig(cacheNames = "customers")
public class CustomerServiceImpl implements CustomerDataService
{

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static final String COULD_NOT_FIND_CUSTOMER = "Could not find a customer with this number: ";

    private CustomersRepository customersRepo;

    @Autowired
    public CustomerServiceImpl(CustomersRepository customersRepo)
    {
        this.customersRepo = customersRepo;
    }

    @Override
    @Cacheable
    public List<Customer> getCustomers()
    {
            return customersRepo.findAll().stream().map(c -> new Customer(
                    new CustomerNumber(c.getCustomerNumber()), c.getContactFirstName(),
                    c.getContactLastName(), c.getPhone(),
                    new Address(c.getAddressLine1(), c.getCity(), c.getState(),
                            c.getCountry(), c.getPostalCode())))
                    .collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#customerNumber.number")
    public Customer getCustomerByNumber(CustomerNumber customerNumber) throws CustomerNumberNotFoundException
    {

        Optional<CustomersEntity> result = customersRepo.findById(customerNumber.number);

        if (result.isPresent())
        {
            CustomersEntity c = result.get();
            return new Customer(
                    new CustomerNumber(c.getCustomerNumber()), c.getContactFirstName(),
                    c.getContactLastName(), c.getPhone(),
                    new Address(c.getAddressLine1(), c.getCity(), c.getState(),
                            c.getCountry(), c.getPostalCode()));
        }
        throw new CustomerNumberNotFoundException(COULD_NOT_FIND_CUSTOMER + customerNumber);
    }


    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
