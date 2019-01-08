package com.yador.meetingmanagerbe.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class User implements UserDetails {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    @JsonIgnore
    private String password;

    @JsonProperty("id")
    @JsonIgnore
    private Integer id;

    public User() {
        super();
    }

    @JsonCreator
    public User(@JsonProperty("id") final Integer id,
                @JsonProperty("username") final String username,
                @JsonProperty("password") final String password) {
        super();
        this.id = id;
        this.username = requireNonNull(username);
        this.password = requireNonNull(password);
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public User setIdAndReturn(Integer id) {
        this.id = id;
        return this;
    }

    public User setUsernameAndReturn(String username) {
        this.username = username;
        return this;
    }

    public User setPasswordAndReturn(String password) {
        this.password = password;
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
