package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.PasswordFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import static com.technokratos.validation.constants.PasswordFormatConst.*;

public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, String> {
    private StringBuilder passwordRegex;

    @Override
    public void initialize(PasswordFormat constraintAnnotation) {
        passwordRegex = new StringBuilder();
        passwordRegex.append(BEGIN_PASSWORD_REGEX);
        if(constraintAnnotation.containOneDigit()) {
            passwordRegex.append(DIGIT_REGEX);
        }
        if(constraintAnnotation.containOneLowercaseLetter()) {
            passwordRegex.append(LOWER_CASE_REGEX);
        }
        if(constraintAnnotation.containOneUppercaseLetter()) {
            passwordRegex.append(UPPER_CASE_REGEX);
        }
        if(constraintAnnotation.containSpecialChar()) {
            passwordRegex.append(generateSpecialCharsRegex(constraintAnnotation.specialChars()));
        }
        passwordRegex.append(PREFIX_SIZE);
        passwordRegex.append(constraintAnnotation.minSize());
        passwordRegex.append(SEPARATOR_SIZE);
        passwordRegex.append(constraintAnnotation.maxSize());
        passwordRegex.append(SUFFIX_SIZE);
        passwordRegex.append(END_PASSWORD_REGEX);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password == null || Pattern.compile(passwordRegex.toString()).matcher(password).matches();
    }

    /** Метод, генерирующий регулярное выражение для спец символов */
    private String generateSpecialCharsRegex(char[] specialChars) {
        return PREFIX_SPECIAL_CHAR + String.valueOf(specialChars) + SUFFIX_SPECIAL_CHAR;
    }

}
