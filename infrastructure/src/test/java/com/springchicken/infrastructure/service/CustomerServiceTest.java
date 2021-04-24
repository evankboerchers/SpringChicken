package com.springchicken.infrastructure.service;

import com.springchicken.infrastructure.entities.CustomersEntity;
import com.springchicken.infrastructure.repos.CustomersRepository;
import com.springchicken.infrastructure.customer.v1.CustomerServiceImpl;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest
{

    @Mock
    CustomersRepository customersRepository;

    @InjectMocks
    CustomerServiceImpl customerService;


    @Test
    public void emptyRepositoryTest()
    {
        assertThat(customersRepository.findAll().isEmpty()).isTrue();
    }

    @Test
    public void returnCustomerTest() throws CustomerNumberNotFoundException
    {

        CustomersEntity testCustomer = new CustomersEntity(123, "testName", "testFirst",
                "testLast", "testPhone", "testAddress1", "testAddress2", "testCity", "testState",
                "testPostal", "testCountry", new BigDecimal("0.1"));

        when(customersRepository.findById(123)).thenReturn(Optional.of(testCustomer));

        Customer test = customerService.getCustomerByNumber(new CustomerNumber(123));

        assertThat(test.getFirstName()).isSameAs(testCustomer.getContactFirstName());
    }

}
