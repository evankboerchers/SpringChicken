package com.springchicken.presentation.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * Web security configuration class, which runs at start-up and determines what filters are added
 * to the security chain (if any) and can also determine a global authentication provider or database
 * (not used as authentication is done in the custom http filter).
 * Commented out are capabilities to specify certain authorities earlier in the filter chain
 * (note that if these are used the @PreAuthorize annotation will still be applied,
 * but this occurs right before method access). Within this configuration all users must be authenticated,
 * except requests made to specific open endpoints, as shown by “/anybody”
 * (note these endpoints also must be given the @PreAuthorize(permitAll()) annotation to function).
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/anybody").permitAll() //must be included to enable access
//                .antMatchers("/service").permitAll() //this gets overridden by the method annotation
//                .antMatchers("/service").hasRole("ADMIN")
//                .antMatchers("/home").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated(); //all requests require authentication before accessing the method security
        http.addFilterAfter(new CustomHTTPFilter(),
                SecurityContextPersistenceFilter.class);
    }

}
