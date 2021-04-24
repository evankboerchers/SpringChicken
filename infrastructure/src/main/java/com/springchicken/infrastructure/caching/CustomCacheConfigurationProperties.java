package com.springchicken.infrastructure.caching;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for cache configuration properties
 */
@ConfigurationProperties(prefix = "cache")
@SuppressWarnings("checkstyle:magicnumber")
@Data
public class CustomCacheConfigurationProperties
{

    private long timeoutSeconds = 60;
    private int redisPort = 6379;
    private String redisHost = "localhost";

    private Map<String, Long> cacheExpirations = new HashMap<>();
}
