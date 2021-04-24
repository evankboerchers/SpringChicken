package com.springchicken.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class for application, handles creating application context, and configuring needed beans
 */
@Configuration
@EnableAutoConfiguration()
@ComponentScan(basePackages = {"com.springchicken"})
@EntityScan(basePackages = {"com.springchicken"})
@EnableCaching
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Main
{
    /**
     * Main method for application
     * @param args Input parameters
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}
