package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.PhoneFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.technokratos.validation.constants.PhoneFormatConst.*;

public class PhoneFormatValidator implements ConstraintValidator<PhoneFormat, String> {

    private StringBuilder phoneRegex;

    @Override
    public void initialize(PhoneFormat constraintAnnotation) {
        phoneRegex = new StringBuilder();
        phoneRegex.append(BEGIN_PHONE_REGEX);
        if(constraintAnnotation.startsWithPlus()) {
            phoneRegex.append(BEGIN_SYMBOL_REGEX);
        }
        phoneRegex.append(DIGIT_REGEX);
        phoneRegex.append(PREFIX_SIZE);
        phoneRegex.append(constraintAnnotation.minSize());
        phoneRegex.append(SEPARATOR_SIZE);
        phoneRegex.append(constraintAnnotation.maxSize());
        phoneRegex.append(SUFFIX_SIZE);
        phoneRegex.append(END_PHONE_REGEX);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return Objects.isNull(phone) || Pattern.compile(phoneRegex.toString()).matcher(phone).matches();
    }
}