package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.LatitudeFormat;
import com.technokratos.validation.annotations.LongitudeFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.technokratos.validation.constants.RealNumberFormatConst.REGEX_REAL_NUMBER;

public class LongitudeFormatValidator implements ConstraintValidator<LongitudeFormat, String> {

    private static final int RANGE = 180;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (Objects.isNull(value)) {
            return true;
        }

        if (!Pattern.compile(REGEX_REAL_NUMBER).matcher(value).matches()) {
            return false;
        }

        BigDecimal longitude = new BigDecimal(value);
        return longitude.abs().compareTo(new BigDecimal(RANGE)) != 1;

    }
}
