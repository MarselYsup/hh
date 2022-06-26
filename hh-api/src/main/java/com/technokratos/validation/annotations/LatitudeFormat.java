package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.LatitudeFormatValidator;
import com.technokratos.validation.validators.PasswordFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Аннотация для проверки правильности координат широты */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LatitudeFormatValidator.class)
public @interface LatitudeFormat {

    /** Сообщение об ошибке */
    String message() default "Latitude is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
