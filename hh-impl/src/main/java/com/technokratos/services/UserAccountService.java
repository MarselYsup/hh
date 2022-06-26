package com.technokratos.services;

import com.technokratos.models.UserAccountEntity;

public interface UserAccountService {

    UserAccountEntity getUserAccountByEmail(String email);

}
