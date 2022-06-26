package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.LatitudeFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.technokratos.validation.constants.RealNumberFormatConst.REGEX_REAL_NUMBER;

public class LatitudeFormatValidator implements ConstraintValidator<LatitudeFormat, String> {

    private static final int RANGE = 90;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (!Pattern.compile(REGEX_REAL_NUMBER).matcher(value).matches()) {
            return false;
        }

        BigDecimal latitude = new BigDecimal(value);
        return latitude.abs().compareTo(new BigDecimal(RANGE)) != 1;

    }
}
