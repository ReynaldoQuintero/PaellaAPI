/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.auth;

import io.swagger.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author reyna
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    
    public CustomUserDetails(User findByEmail) {
        this.username = findByEmail.getEmail();
        this.password = findByEmail.getPassword();
        List<GrantedAuthority> roleArray = new ArrayList();
        roleArray.add(new SimpleGrantedAuthority(findByEmail.getPrivilege().name()));
        this.authorities = roleArray;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return password; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        return username; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
       return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
}
