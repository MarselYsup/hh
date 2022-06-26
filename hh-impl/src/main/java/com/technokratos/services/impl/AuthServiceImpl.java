package com.technokratos.services.impl;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.ChangePasswordResponse;
import com.technokratos.dto.response.SignInResponse;
import com.technokratos.dto.response.SignUpResponse;
import com.technokratos.exceptions.already_exist.EmailAlreadyExistHhException;
import com.technokratos.exceptions.not_found.TokenNotFoundException;
import com.technokratos.exceptions.not_found.UserAccountNotFoundException;
import com.technokratos.exceptions.oldPassword.OldPasswordException;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.exceptions.unauthorized.NotConfirmedUnauthorizedHhException;
import com.technokratos.exceptions.unauthorized.UserAccountUnauthorizedException;
import com.technokratos.mappers.SignUpMapper;
import com.technokratos.enums.Role;
import com.technokratos.enums.Status;
import com.technokratos.repositories.RefreshTokenRepository;
import com.technokratos.repositories.UserAccountRepository;
import com.technokratos.services.AuthService;
import com.technokratos.services.TokenService;
import com.technokratos.util.CodeGenerator;
import com.technokratos.util.EmailUtil;
import com.technokratos.util.MailGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TokenService tokenService;

    private final UserAccountRepository userAccountRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final SignUpMapper signUpMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailUtil emailUtil;

    @Override
    public SignInResponse authenticate(SignInRequest signInRequest) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(signInRequest.getEmail())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(),user.getPassword()))
                .filter(user -> checkUserStatus(user.getStatus()))
                .orElseThrow(UserAccountUnauthorizedException::new);

        return tokenService.createTokens(userAccount);
    }

    @Override
    public SignInResponse updateAccessTokenWithRefreshToken(RefreshTokenRequest tokenRequest) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findById(tokenRequest.getRefreshToken())
                .orElseThrow(TokenNotFoundException::new);

        tokenService.checkExpiredTimeRefreshToken(refreshToken);

        UserAccountEntity userAccount = userAccountRepository.findByRefreshToken(refreshToken)
                .orElseThrow(UserAccountNotFoundException::new);

        return tokenService.createTokens(userAccount);
    }


    @Override
    public SignUpResponse signUp(SignUpEmployeeRequest signUp) {
        return signUp(SignUpRequest.builder()
                .email(signUp.getEmail())
                .password(signUp.getPassword())
                .build(), Role.EMPLOYEE);
    }

    @Override
    public SignUpResponse signUp(SignUpCompanyRequest signUp) {
       return signUp(SignUpRequest.builder()
               .email(signUp.getEmail())
               .password(signUp.getPassword())
               .build(),Role.COMPANY);
    }

    private SignUpResponse signUp(SignUpRequest signUp, Role role) {
        if(userAccountRepository.existsByEmail(signUp.getEmail())) {
            throw new EmailAlreadyExistHhException();
        }
        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        UserAccountEntity userAccount = signUpMapper.toEntity(signUp);
        userAccount.setConfirmCode(UUID.randomUUID().toString());
        userAccount.setStatus(Status.NOT_CONFIRMED);
        userAccount.setRole(role);
        userAccount = userAccountRepository.save(userAccount);

        emailUtil.sendMail(MailGenerator.generateConfirmSignUpMessage(userAccount));

        return signUpMapper.toResponse(userAccount);
    }

    @Override
    public SignUpResponse checkConfirmCode(String code) {
        return signUpMapper.toResponse(userAccountRepository.save(submitConfirmCode(code)));
    }

    @Override
    @Transactional
    public void changePassword(PasswordRecoveryRequest passwordRecoveryRequest) {
        UserAccountEntity userAccount = userAccountRepository
                .findByEmail(passwordRecoveryRequest.getEmail())
                .orElseThrow(UserAccountNotFoundException::new);
        userAccount.setStatus(Status.NOT_CONFIRMED);
        userAccount.setConfirmCode(CodeGenerator.generate());
        userAccountRepository.save(userAccount);
        emailUtil.sendMail(MailGenerator.generateConfirmPasswordMessage(userAccount));
    }

    @Override
    @Transactional
    public ChangePasswordResponse confirmChangePassword(ChangePasswordRequest changePasswordRequest) {
        UserAccountEntity userAccount = submitConfirmCode(changePasswordRequest.getConfirmCode());
        if(passwordEncoder.matches(changePasswordRequest.getNewPassword(), userAccount.getPassword())){
            throw new OldPasswordException();
        }
        userAccount.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        return ChangePasswordResponse
                .builder()
                .email(userAccount.getEmail())
                .state(userAccount.getStatus().toString())
                .build();
    }

    @Override
    public SignInResponse authenticateByOAuth(OAuth2AuthenticationToken authentication) {
        String email = "TEMPORARY" + authentication.getName() + "@oauth.com";

        UserAccountEntity user = userAccountRepository.findByEmail(email)
                .orElseGet(() -> UserAccountEntity.builder()
                        .email(email)
                        .role(Role.EMPLOYEE)
                        .status(Status.CONFIRMED)
                        .build());

        String password = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(password));
        userAccountRepository.save(user);

        SignInRequest request = SignInRequest.builder()
                .email(user.getEmail())
                .password(password)
                .build();

        return authenticate(request);
    }

    private boolean checkUserStatus(Status status) {
        if (status.equals(Status.NOT_CONFIRMED)) {
            throw new NotConfirmedUnauthorizedHhException();
        }
        return true;
    }

    private UserAccountEntity submitConfirmCode(String confirmCode) {
        UserAccountEntity userAccount = userAccountRepository.findByConfirmCode(confirmCode)
                .orElseThrow(UserAccountNotFoundException::new);
        userAccount.setConfirmCode(null);
        userAccount.setStatus(Status.CONFIRMED);
        return userAccount;
    }

}
