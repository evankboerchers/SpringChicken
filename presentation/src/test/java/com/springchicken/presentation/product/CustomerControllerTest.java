package com.springchicken.presentation.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springchicken.logic.CustomerDataService;
import com.springchicken.logic.OrderDataService;
import com.springchicken.logic.dao.Customer;
import com.springchicken.logic.dao.Order;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;

import com.springchicken.presentation.product.v1.models.CustomerResponseDTO;
import com.springchicken.presentation.product.v1.models.OrdersResponseDTO;
import com.springchicken.presentation.util.PresentationTestConfiguration;
import com.springchicken.presentation.util.DefaultTestObjects;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.http.HttpHeaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest
@Import(CustomerController.class)
@ContextConfiguration(classes = PresentationTestConfiguration.class)
@SuppressFBWarnings(value="NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR")
public class CustomerControllerTest
{

    private static final String RESOURCE_PATH = "/customer/v1";
    private static final String CUSTOMER_NUMBER_RESOURCE_PATH = RESOURCE_PATH + "/";
    private static final String ORDERS_RESOURCE_PATH = RESOURCE_PATH + "/orders/";
    private static final String VALID_BEARER_TOKEN = "Bearer 42";
    private static final String EMPTY_BEARER_TOKEN = "Bearer";
    private static final String MALFORMED_BEARER_TOKEN = "MissingBearer 42";
    private static final String INVALID_BEARER_TOKEN = "Bearer 43";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerDataService customerDataService;

    @MockBean
    private OrderDataService orderDataService;

    private static class ListCustomerResponseDTOReader extends TypeReference<List<CustomerResponseDTO>> {}

    @Test
    public void missingAuth401() throws Exception
    {
        this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    public void emptyAuth401() throws Exception
    {


        this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH).header(HttpHeaders.AUTHORIZATION, EMPTY_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isForbidden());


    }

    @Test
    public void malformedAuth401() throws Exception
    {
        this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH).header(HttpHeaders.AUTHORIZATION, MALFORMED_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    public void invalidAuth401() throws Exception
    {

            this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH).header(HttpHeaders.AUTHORIZATION, INVALID_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isForbidden());



    }

    private void assertCustomerEqualsCustomerResponseDTO(Customer customer, CustomerResponseDTO dto)
    {
        assertEquals(customer.firstName + customer.lastName, dto.getCustomerName());
        assertEquals(customer.customerNumber.number, dto.getCustomerGuid());
        assertEquals(customer.firstName, dto.getContactInformation().getContactFirstName());
        assertEquals(customer.lastName, dto.getContactInformation().getContactLastName());
        assertEquals(customer.phoneNumber, dto.getContactInformation().getPhoneNumber());
        assertEquals(customer.address.streetAddress, dto.getAddressLine1());
        assertEquals(customer.address.city, dto.getCity());
        assertEquals(customer.address.state, dto.getState());
        assertEquals(customer.address.postalCode, dto.getPostalCode());
        assertEquals(customer.address.country, dto.getCountry());
    }

    @Test
    public void listCustomersWithList() throws Exception
    {
        List<Customer> customers = List.of(DefaultTestObjects.VALID_CUSTOMER);
        given(customerDataService.getCustomers()).willReturn(customers);

        ResultActions resultActions = this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        List<CustomerResponseDTO> responseDTO = objectMapper.readValue(content, new ListCustomerResponseDTOReader());

        assertEquals(customers.size(), responseDTO.size());

        for (int i = 0; i < customers.size(); i++)
        {
            assertCustomerEqualsCustomerResponseDTO(customers.get(i), responseDTO.get(i));
        }

    }

    @Test
    public void listCustomersEmptyList() throws Exception
    {
        List<Customer> customers = Collections.emptyList();
        given(this.customerDataService.getCustomers()).willReturn(customers);

        ResultActions resultActions = this.mvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());


        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        List<CustomerResponseDTO> responseDTO = objectMapper.readValue(content, new ListCustomerResponseDTOReader());


        assertTrue(responseDTO.isEmpty());

    }

    @Test
    public void getCustomerValidId() throws Exception
    {
        when(this.customerDataService.getCustomerByNumber(DefaultTestObjects.VALID_CUSTOMER_NUMBER)).thenReturn(DefaultTestObjects.VALID_CUSTOMER);


        ResultActions resultActions = this.mvc.perform(MockMvcRequestBuilders.get(CUSTOMER_NUMBER_RESOURCE_PATH + DefaultTestObjects.VALID_CUSTOMER_NUMBER.number)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        CustomerResponseDTO responseDTO = objectMapper.readValue(content, CustomerResponseDTO.class);

        assertCustomerEqualsCustomerResponseDTO(DefaultTestObjects.VALID_CUSTOMER, responseDTO);

    }

    @Test
    public void getCustomerInvalidId() throws Exception
    {
        given(this.customerDataService.getCustomerByNumber(DefaultTestObjects.INVALID_CUSTOMER_NUMBER))
                .willThrow(CustomerNumberNotFoundException.class);

        this.mvc.perform(MockMvcRequestBuilders.get(CUSTOMER_NUMBER_RESOURCE_PATH + DefaultTestObjects.INVALID_CUSTOMER_NUMBER.number)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @SuppressFBWarnings(value = { "PRMC_POSSIBLY_REDUNDANT_METHOD_CALLS" }, justification = "any() returns a different matcher each time")
    public void getOrdersValidId() throws CustomerNumberNotFoundException, Exception
    {
        List<Customer> customers = List.of(DefaultTestObjects.VALID_CUSTOMER);


        given(this.customerDataService.getCustomers()).willReturn(customers);
        given(this.customerDataService.getCustomerByNumber(DefaultTestObjects.VALID_CUSTOMER_NUMBER)).willReturn(DefaultTestObjects.VALID_CUSTOMER);

        List<Order> orders = List.of(DefaultTestObjects.ORDER_ONE, DefaultTestObjects.ORDER_TWO, DefaultTestObjects.ORDER_THREE);
        given(this.orderDataService.getOrders(eq(DefaultTestObjects.VALID_CUSTOMER_NUMBER), any(), any())).willReturn(orders);

        ResultActions resultActions = this.mvc.perform(MockMvcRequestBuilders.get(ORDERS_RESOURCE_PATH + DefaultTestObjects.VALID_CUSTOMER_NUMBER.number)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        OrdersResponseDTO responseDTO = objectMapper.readValue(content, OrdersResponseDTO.class);

        assertCustomerEqualsCustomerResponseDTO(DefaultTestObjects.VALID_CUSTOMER, responseDTO.getCustomer());

        assertEquals(orders.size(), responseDTO.getOrders().size());



        // TODO: This test is a bit weak. Should probably also confirm the order contents as well.
    }

    @Test
    @SuppressFBWarnings(value = {"PRMC_POSSIBLY_REDUNDANT_METHOD_CALLS" }, justification = "any() returns a different matcher each time")
    public void getOrdersInvalidId() throws Exception
    {
        given(customerDataService.getCustomerByNumber(DefaultTestObjects.INVALID_CUSTOMER_NUMBER)).willThrow(CustomerNumberNotFoundException.class);
        given(orderDataService.getOrders(eq(DefaultTestObjects.INVALID_CUSTOMER_NUMBER), any(), any())).willThrow(CustomerNumberNotFoundException.class);

        this.mvc.perform(MockMvcRequestBuilders.get(ORDERS_RESOURCE_PATH + DefaultTestObjects.INVALID_CUSTOMER_NUMBER.number)
                .header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}