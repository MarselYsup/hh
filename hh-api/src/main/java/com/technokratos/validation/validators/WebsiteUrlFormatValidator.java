package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.WebsiteUrlFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.technokratos.validation.constants.WebsiteUrlFormatConst.*;

public class WebsiteUrlFormatValidator implements ConstraintValidator<WebsiteUrlFormat, String> {

    private StringBuilder urlRegex;

    @Override
    public void initialize(WebsiteUrlFormat constraintAnnotation) {
        urlRegex = new StringBuilder();
        urlRegex.append(BEGIN_PROTOCOL_REGEX);
        if(constraintAnnotation.containHttp() && constraintAnnotation.containHttps()) {
            urlRegex.append(HTTP_REGEX);
            urlRegex.append(SEPARATOR_PROTOCOL);
            urlRegex.append(HTTPS_REGEX);
        } else if(constraintAnnotation.containHttp()) {
            urlRegex.append(HTTPS_REGEX);
        } else if(constraintAnnotation.containHttps()) {
            urlRegex.append(HTTPS_REGEX);
        } else {
            throw new IllegalArgumentException(ERROR_PROTOCOL);
        }
        urlRegex.append(END_PROTOCOL_REGEX);
        urlRegex.append(DOMAIN_REGEX);
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return url == null || Pattern.compile(urlRegex.toString()).matcher(url).matches();
    }
}
