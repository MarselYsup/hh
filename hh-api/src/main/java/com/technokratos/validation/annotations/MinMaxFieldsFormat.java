package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.MinMaxFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Аннотация для проверки, того что в классе поле с минимальным значением не превосходит поле с максимальным*/


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinMaxFieldsValidator.class)
public @interface MinMaxFieldsFormat {

    String minField();

    String maxField();

    /** Сообщение об ошибке */
    String message() default "{minField} must be less or equal to {maxField}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
