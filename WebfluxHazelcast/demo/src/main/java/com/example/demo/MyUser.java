package com.example.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class MyUser implements UserDetails {
    @Id
    private Integer id;
    private String username;
    private String password;
    private boolean active = true;
    // private Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
    private String roles;

    @Builder
    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = "ROLE_USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return roles;
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return active;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return active;
    }

}
