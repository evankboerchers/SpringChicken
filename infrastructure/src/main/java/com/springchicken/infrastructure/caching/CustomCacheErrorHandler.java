package com.springchicken.infrastructure.caching;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * {@inheritDoc}
 */
public class CustomCacheErrorHandler implements CacheErrorHandler
{

    private static final Logger logger = LoggerFactory.getLogger(CustomCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object o)
    {
        logger.error("Encounter error during cache get: ", e);
    }

    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1)
    {
        logger.error("Encountered error during cache put: ", e);
    }

    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object o)
    {
        logger.error("Encountered error during cache evict ", e);
    }

    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache)
    {
        logger.error("Encountered error during cache clear ", e);
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

}
