package com.springchicken.presentation.util;

import com.springchicken.presentation.auth.CustomGlobalMethodSecurityConfiguration;
import com.springchicken.presentation.auth.CustomWebSecurityConfigurerAdapter;
import com.springchicken.presentation.exceptions.GlobalExceptionHandler;
import com.springchicken.presentation.shutdown.Status;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresentationTestConfiguration
{

   @Bean
   public CustomWebSecurityConfigurerAdapter securityAdapter()
   {
       return new CustomWebSecurityConfigurerAdapter();
   }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler()
    {
        return new GlobalExceptionHandler();
    }

    @Bean
    public CustomGlobalMethodSecurityConfiguration customSecurityConfiguration()
    {
        return new CustomGlobalMethodSecurityConfiguration();
    }

    @Bean
    @SuppressFBWarnings(value = {"WI_MANUALLY_ALLOCATING_AN_AUTOWIRED_BEAN"}, justification = "Auto-wiring functioning as intended")
    public Status applicationStatus()
    {
        return new Status();
    }

}
