package com.springchicken.presentation.config;

import com.springchicken.presentation.shutdown.CustomShutdown;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Context Configuration for presentation layer
 */
@Configuration
public class PresentationConfig
{

    /**
     *
     * @param gracefulShutdown The custom shutdown hook
     * @return WebServletFactory
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final CustomShutdown gracefulShutdown)
    {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }
}
