package com.springchicken.presentation.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;
import javax.ws.rs.core.HttpHeaders;

/**
 * Custom filter that will read the authentication header and, if it is even,
 * will assign an authorization class to the security context, indicating that this user has been authenticated,
 * and giving them a security role that will be checked against the @PreAuthorize annotation on the controller classes.
 * This filter allows all requests through, even the unauthorized ones,
 * in order to allow deliberately unsecured endpoints to be accessed
 * (there is no way within the filter to determine what annotation is on the endpoint,
 * so no way to know whether it is open or restricted).
 */
@SuppressWarnings("checkstyle:magicnumber")
public class CustomHTTPFilter extends HttpFilter
{
    private static final long serialVersionUID = 2;
    private static final Logger logger = LoggerFactory.getLogger(CustomHTTPFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException
    {
        logger.info("Request URL: {} \nAuthentication header {}", request.getRequestURL(), request.getHeader("Authorization"));
        try
        {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (null != authHeader)
            {
                String bearerToken = authHeader.toUpperCase(Locale.ROOT)
                        .replace("BEARER ", "");
                Integer bearerInt = Integer.valueOf(bearerToken);
                SecurityContext temp = SecurityContextHolder.getContext();
                if (bearerInt % 2 == 0 && temp.getAuthentication() == null)
                {
                        if (bearerInt >= 100)
                        {
                            temp.setAuthentication(new CustomAuthentication("ADMIN"));
                            logger.info("Authenticated an ADMIN!");
                        }
                        else
                            {
                            temp.setAuthentication(new CustomAuthentication("USER"));
                            logger.info("Authenticated a USER!");
                        }
                }
                else
                    {
                        temp.setAuthentication(null); //empty security authentication - easy way to revoke access
                }
            }
        }
        catch (NumberFormatException exc)
        {
            logger.error("encountered exception while trying to authorize: ", exc);
        }
        chain.doFilter(request, response);
    }
}