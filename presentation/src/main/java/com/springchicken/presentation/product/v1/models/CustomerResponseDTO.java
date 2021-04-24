package com.springchicken.presentation.product.v1.models;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.springchicken.logic.dao.Customer;


/**
 * Main DTO containing information for responses about Customers
 */
public class CustomerResponseDTO
{
    public static final CustomerResponseDTO EMPTY = new CustomerResponseDTO();

    Integer customerGuid;
    String customerName;
    Contact contactInformation;
    @JsonProperty("addressLine")
    String addressLine1;
    String city;
    String state;
    String postalCode;
    String country;

    private CustomerResponseDTO()
    {
        customerGuid = 0;
        customerName = StringUtils.EMPTY;
        contactInformation = Contact.EMPTY;
        addressLine1 = StringUtils.EMPTY;
        city = StringUtils.EMPTY;
        state = StringUtils.EMPTY;
        postalCode = StringUtils.EMPTY;
        country = StringUtils.EMPTY;
    }

    public CustomerResponseDTO(Customer customer)
    {
        customerGuid = customer.customerNumber.number;
        customerName = customer.firstName + customer.lastName;
        contactInformation = new Contact(customer);
        addressLine1 = customer.address.streetAddress;
        city = customer.address.city;
        state = customer.address.state;
        postalCode = customer.address.postalCode;
        country = customer.address.country;

    }

    public Integer getCustomerGuid()
    {
        return customerGuid;
    }

    public void setCustomerGuid(Integer customerGuid)
    {
        this.customerGuid = customerGuid;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public Contact getContactInformation()
    {
        return contactInformation;
    }

    public void setContactInformation(Contact contactInformation)
    {
        this.contactInformation = contactInformation;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * Entity equals method
     *
     * @param o other object to compare with
     *
     * @return true if this entity equals the other
     */
    @Override
    public boolean equals(Object o)
    {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * Entity hashcode method
     *
     * @return Hash of the unique identifier
     */
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Entity toString method
     *
     * @return String representation of this entity
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
