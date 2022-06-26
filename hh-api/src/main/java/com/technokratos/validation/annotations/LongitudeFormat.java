package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.LatitudeFormatValidator;
import com.technokratos.validation.validators.LongitudeFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongitudeFormatValidator.class)
public @interface LongitudeFormat {

    /** Сообщение об ошибке */
    String message() default "Longitude is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
