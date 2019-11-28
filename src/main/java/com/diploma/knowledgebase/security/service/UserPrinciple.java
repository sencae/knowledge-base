package com.diploma.knowledgebase.security.service;

import com.diploma.knowledgebase.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserPrinciple implements UserDetails {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private UserPrinciple(Long id, String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(
                        user.getUserGroupsByRegId().getGroupName()
                )
        );

        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}
