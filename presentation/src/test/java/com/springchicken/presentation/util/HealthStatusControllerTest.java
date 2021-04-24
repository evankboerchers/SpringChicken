package com.springchicken.presentation.util;

import com.springchicken.presentation.HealthStatusController;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * Test class for Health controller
 */
@WebMvcTest
@Import(HealthStatusController.class)
@ContextConfiguration(classes = PresentationTestConfiguration.class)
public class HealthStatusControllerTest
{

    private static final String RESOURCE_PATH = "/health";
    private static final String VALID_BEARER_TOKEN = "Bearer 42";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void healthReturns200WithoutAuth() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_PATH).header(HttpHeaders.AUTHORIZATION, VALID_BEARER_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
