package com.technokratos.util;

import com.technokratos.models.UserAccountEntity;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class MailGenerator {
    private final String MESSAGE_SUBJECT = "confirm";

    private final String SIGN_UP_MESSAGE_TEMPLATE = "confirm-mail";

    private final String CHANGE_PASSWORD_MESSAGE_TEMPLATE = "password-confirm";

    private final String DEFAULT_NAME_EMAIL = "email";

    private final String DEFAULT_NAME_CONFIRM_LINK = "confirmLink";

    private final String DEFAULT_NAME_CONFIRM_CODE = "confirmCode";

    private final String DEFAULT_CONFIRM_PATH = "http://localhost:8080/hh/v1/auth/";

    public InfoMail generateConfirmSignUpMessage(UserAccountEntity userAccount) {
        return InfoMail.builder()
                .to(userAccount.getEmail())
                .subject(MESSAGE_SUBJECT)
                .templateName(SIGN_UP_MESSAGE_TEMPLATE)
                .data(generateDateForSignUpMessage(userAccount))
                .build();
    }

    public InfoMail generateConfirmPasswordMessage(UserAccountEntity userAccount) {
        return InfoMail.builder()
                .to(userAccount.getEmail())
                .subject(MESSAGE_SUBJECT)
                .templateName(CHANGE_PASSWORD_MESSAGE_TEMPLATE)
                .data(generateDateForPasswordMessage(userAccount))
                .build();
    }

    private Map<String, String> generateDateForSignUpMessage(UserAccountEntity userAccount) {
        return Map.of(DEFAULT_NAME_EMAIL, userAccount.getEmail(),
                DEFAULT_NAME_CONFIRM_LINK,DEFAULT_CONFIRM_PATH + userAccount.getConfirmCode());
    }

    private Map<String, String> generateDateForPasswordMessage(UserAccountEntity userAccount) {
        return Map.of(DEFAULT_NAME_EMAIL, userAccount.getEmail(),
                DEFAULT_NAME_CONFIRM_CODE, userAccount.getConfirmCode());
    }
}
