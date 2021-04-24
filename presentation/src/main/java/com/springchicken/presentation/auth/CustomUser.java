package com.springchicken.presentation.auth;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Handles the user’s credentials, though it does contain authority information,
 * this information is not checked by the authority annotations.
 * This class is included more for completeness than functionality
 */
public class CustomUser implements UserDetails
{
    private static final long serialVersionUID = 3;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorityList;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> list)
    {
        this.userName = username;
        this.password = password;
        this.authorityList = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorityList; //not used to check authorities
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(Object o)
    {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
