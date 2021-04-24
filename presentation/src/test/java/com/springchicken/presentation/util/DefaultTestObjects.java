package com.springchicken.presentation.util;


import java.time.LocalDate;
import java.util.List;

import com.springchicken.logic.dao.Address;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.CustomerNumber;
import com.springchicken.logic.dao.Order;
import com.springchicken.logic.dao.OrderEntry;
import com.springchicken.logic.dao.Product;

public final class DefaultTestObjects
{
    public static final CustomerNumber VALID_CUSTOMER_NUMBER = new CustomerNumber(10);
    public static final CustomerNumber INVALID_CUSTOMER_NUMBER = new CustomerNumber(11);

    public static final String VALID_STREET_ADDRESS = "123 Home Road";
    public static final String VALID_CITY = "Fakecity";
    public static final String VALID_STATE = "Fakestate";
    public static final String VALID_COUNTRY = "Fakecountry";
    public static final String VALID_POSTAL_CODE = "A1A 1A1";
    public static final Address
            VALID_ADDRESS = new Address(VALID_STREET_ADDRESS, VALID_CITY, VALID_STATE, VALID_COUNTRY, VALID_POSTAL_CODE);

    public static final String VALID_FIRST_NAME = "first";
    public static final String VALID_LAST_NAME = "surname";
    public static final String VALID_PHONE_NUMBER = "403-123-4567";
    public static final Customer VALID_CUSTOMER = new Customer(VALID_CUSTOMER_NUMBER, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER,
            VALID_ADDRESS);

    public static final Product PRODUCT_ONE = new Product("P1", "Product 1 Description");
    public static final Product PRODUCT_TWO = new Product("P2", "Product 2 Description");
    public static final Product PRODUCT_THREE = new Product("P3", "Product 3 Description");

    public static final LocalDate ORDER_TWO_DATE = LocalDate.now();
    public static final List<OrderEntry> ORDER_TWO_ORDER_ENTRIES = List.of(
            new OrderEntry(1, PRODUCT_ONE),
            new OrderEntry(2, PRODUCT_TWO),
            new OrderEntry(3, PRODUCT_THREE)
    );

    public static final LocalDate ORDER_ONE_DATE = ORDER_TWO_DATE.minusDays(1);
    public static final List<OrderEntry> ORDER_ONE_ORDER_ENTRIES = List.of(
            new OrderEntry(3, PRODUCT_ONE),
            new OrderEntry(1, PRODUCT_TWO),
            new OrderEntry(2, PRODUCT_THREE)
    );

    public static final LocalDate ORDER_THREE_DATE = ORDER_TWO_DATE.plusDays(1);
    public static final List<OrderEntry> ORDER_THREE_ORDER_ENTRIES = List.of(
            new OrderEntry(5, PRODUCT_ONE),
            new OrderEntry(6, PRODUCT_THREE)
    );

    public static final Order ORDER_ONE = new Order(VALID_CUSTOMER, ORDER_ONE_DATE, ORDER_ONE_ORDER_ENTRIES);
    public static final Order ORDER_TWO = new Order(VALID_CUSTOMER, ORDER_TWO_DATE, ORDER_TWO_ORDER_ENTRIES);
    public static final Order ORDER_THREE = new Order(VALID_CUSTOMER, ORDER_THREE_DATE, ORDER_THREE_ORDER_ENTRIES);

    private DefaultTestObjects()
    {
    }
}

