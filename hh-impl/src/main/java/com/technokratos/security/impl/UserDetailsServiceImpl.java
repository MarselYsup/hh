package com.technokratos.security.impl;

import com.technokratos.exceptions.unauthorized.UserAccountUnauthorizedException;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.repositories.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountEntity userAccount = userAccountRepository.findByEmail(username)
                .orElseThrow(UserAccountUnauthorizedException::new);
        return User.builder()
                .username(userAccount.getEmail())
                .password(userAccount.getPassword())
                .roles(userAccount.getRole().toString())
                .build();
    }
}
