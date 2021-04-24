package com.springchicken.presentation.auth;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Handles the user’s authorities, as denoted by the role, and whether the user is authenticated.
 * This class also contains the user, which could include user specific information.
 * As of now, this class is designed to only handle one role at a time, to demonstrate functionality,
 * but could easily be expanded to handle more.
 */
@SuppressWarnings("checkstyle:")
public class CustomAuthentication implements Authentication
{
    private static final long serialVersionUID = 1;
    private static final String ROLE_PREFIX = "ROLE_";

    private UserDetails theUser;
    private boolean isAuthenticated;

    private String role;

    @SuppressFBWarnings(value = {"PCOA_PARTIALLY_CONSTRUCTED_OBJECT_ACCESS"}, justification = "Method call is a constructor that can't be final")
    public CustomAuthentication(String role)
    {
        theUser = new CustomUser(role,role,getAuthorities());
        isAuthenticated = true;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return list;
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }

    @Override
    public Object getDetails()
    {
        return theUser;
    }

    @Override
    public Object getPrincipal()
    {
        return theUser;
    }

    @Override
    public boolean isAuthenticated()
    {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated)
    {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName()
    {
        return theUser.getUsername();
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
