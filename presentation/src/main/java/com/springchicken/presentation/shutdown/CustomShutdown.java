package com.springchicken.presentation.shutdown;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of graceful shutdown
 */
@Component
@SuppressWarnings("checkstyle:magicnumber")
@SuppressFBWarnings(value = {"MDM_THREAD_YIELD", "USFW_UNSYNCHRONIZED_SINGLETON_FIELD_WRITES", "NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"})
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent>
{

    private static final Logger logger = LoggerFactory.getLogger(CustomShutdown.class);

    private volatile Connector connector;

    private Status status;

    @Value("${server.custom-shutdown.timeout}")
    private long timeout;

    @Value("${server.custom-shutdown.delay}")
    private long delay;

    @Autowired
    public CustomShutdown(Status status)
    {
        this.status = status;
    }

    @Override
    public void customize(Connector connector)
    {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event)
    {
        shutdownDelay();


        logger.info("Commencing graceful shutdown for {} s", timeout);
        logger.info("Completing active requests");

        this.connector.pause();

        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor)
        {
            try
            {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(timeout, TimeUnit.SECONDS))
                {
                    logger.warn("Tomcat thread pool did not shut down gracefully within 30 seconds. Proceeding with forceful shutdown");
                }
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        logger.info("Graceful Shutdown complete");
    }

    private void shutdownDelay()
    {
        logger.info("Shutdown commenced waiting {}s", delay);

        status.setIsShutdown(Boolean.TRUE);

        try
        {
            Thread.sleep(delay*1000);
        }

        catch(InterruptedException e)
        {
            logger.warn("Unable to delay shutdown thread interrupted : ", e);
        }

        logger.info("Shutdown delay complete");
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}

