package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.PasswordFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Аннотация для проверки валидности пароля */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordFormatValidator.class)
public @interface PasswordFormat {

    /** Минимальный размер пароля */
    int minSize() default 8;

    /** Максимальный размер пароля */
    int maxSize() default 20;

    /** специальные символы, которые используется в пароле. по умолчанию :!, @, $, ?, $ */
    char[] specialChars() default {'!','@','$','?'};

    /** Пароль содержит хотя бы одну цифру */
    boolean containOneDigit() default true;

    /** Пароль содержит хотя бы одну букву в низком регистре */
    boolean containOneLowercaseLetter() default true;

    /** Пароль содержит хотя бы одну букву в высоком регистре */
    boolean containOneUppercaseLetter() default true;

    /** Пароль содержит один из специальных символов */
    boolean containSpecialChar() default true;

    /** Сообщение об ошибке */
    String message() default "Password is not valid. " +
            "Password should have at least on digit, lowercase char, uppercase char and special char";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
