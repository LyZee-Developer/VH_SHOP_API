package com.service.BVHSHOP.service;

import com.service.BVHSHOP.model.UserLogin;
import com.service.BVHSHOP.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserLogin userLogin = userLoginRepository.findByUsernameAndIsDisabled(username, false);
        if (userLogin == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new User(userLogin.getUsername(), userLogin.getPassword(), Collections.emptyList());
    }
}