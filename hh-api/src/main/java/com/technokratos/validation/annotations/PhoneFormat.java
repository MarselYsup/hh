package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.PhoneFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneFormatValidator.class)
public @interface PhoneFormat {

    /** Телефонный номер начинается на плюс */
    boolean startsWithPlus() default true;

    /** Минимальная длина телефонного номера */
    int minSize() default 9;

    /** Максимальная длина телефонного номера */
    int maxSize() default 11;

    /** Сообщение об ошибке */
    String message() default "Phone number is not valid! " +
            "It should start with plus, be 9-11 symbols length and contain only digits";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
