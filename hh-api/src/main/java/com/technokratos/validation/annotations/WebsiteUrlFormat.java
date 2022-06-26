package com.technokratos.validation.annotations;

import com.technokratos.validation.validators.WebsiteUrlFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Аннотация для проверки валидности website url*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WebsiteUrlFormatValidator.class)
public @interface WebsiteUrlFormat {

    /** URL содержит http протокол */
    boolean containHttp() default true;

    /** URL содержит https протокол */
    boolean containHttps() default true;

    /** Сообщение об ошибке */
    String message() default "Website url is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
