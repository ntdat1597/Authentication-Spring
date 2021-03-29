package com.example.auth.service;

import com.example.auth.model.Role;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails LoadUserByUsername (String username) {
        User user = userRepository.finByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = null;
        if ( user == null ) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for ( Role role : user.getRoles() ) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));

        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }


    @Override
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
        return null;
    }
}
