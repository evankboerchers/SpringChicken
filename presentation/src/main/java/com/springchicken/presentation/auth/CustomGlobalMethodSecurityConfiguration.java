package com.springchicken.presentation.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * This class launches the customMethodSecurityMetadataSource at start-up,
 * which is responsible for blocking any methods or classes that provide connection to an endpoint,
 * but have not been given any security annotations. (Safeguards for developers missing security annotations)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration
{
    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource()
    {
        return new CustomAuthorityMethodSecurityMetadataSource();
    }

}
