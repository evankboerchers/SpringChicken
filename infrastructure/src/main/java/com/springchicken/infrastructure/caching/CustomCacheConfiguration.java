package com.springchicken.infrastructure.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the redis caching configuration, creates cachemanager/related beans using cache config properties
 */
@Configuration
@EnableConfigurationProperties(CustomCacheConfigurationProperties.class)
public class CustomCacheConfiguration extends CachingConfigurerSupport
{

    private static final Logger logger = LoggerFactory.getLogger(CustomCacheConfiguration.class);

    /**
     * Specifies the error handler to use for cache logging
     * @return Customer cache error handler
     */
    @Override
    public CacheErrorHandler errorHandler()
    {
        return new CustomCacheErrorHandler();
    }

    /**
     * Specifies default configuration properties for the redis cache
     * @param timeoutInSeconds Default time to live for cache
     * @return
     */
    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds)
    {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Creates redis connection with specified properties
     * @param properties The default cache creation properties
     * @return The connection factory
     */
    @Bean
    LettuceConnectionFactory redisConnectionFactory(CustomCacheConfigurationProperties properties)
    {
        logger.info("Redis (/Lettuce) configuration enabled. Cache timeout = {}", properties.getTimeoutSeconds());

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(properties.getRedisHost());
        redisStandaloneConfiguration.setPort(properties.getRedisPort());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * Creates a redis cache configuration from properties
     * @param properties The specified properties for cache
     * @return Redis configuration
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration(CustomCacheConfigurationProperties properties)
    {
        return createCacheConfiguration(properties.getTimeoutSeconds());
    }

    /**
     * Creates a cachemanager using redis connection and the specified configuration properties
     * @param redisConnectionFactory The connection factory
     * @param properties The specified properties for cache creation
     * @return The cachemanager with provided configuration
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CustomCacheConfigurationProperties properties)
    {

        logger.info("Constructing RedisCacheManager");

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>(properties.getCacheExpirations().size());

        for (Map.Entry<String, Long> cacheNameAndTimeout : properties.getCacheExpirations().entrySet())
        {
            cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
        }
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration(properties))
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
}