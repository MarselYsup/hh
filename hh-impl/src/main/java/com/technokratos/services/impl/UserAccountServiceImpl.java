package com.technokratos.services.impl;

import com.technokratos.exceptions.not_found.UserAccountNotFoundException;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.repositories.UserAccountRepository;
import com.technokratos.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccountEntity getUserAccountByEmail(String email) {
        return userAccountRepository.findByEmail(email).orElseThrow(UserAccountNotFoundException::new);
    }
}
