package com.springchicken.infrastructure.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for the infrastrutcure layer. Enables caching and JPA repositories.
 */
@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.springchicken"})
public class InfrastructureConfig
{

}
