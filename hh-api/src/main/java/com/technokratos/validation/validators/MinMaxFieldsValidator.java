package com.technokratos.validation.validators;

import com.technokratos.validation.annotations.MinMaxFieldsFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class MinMaxFieldsValidator implements ConstraintValidator<MinMaxFieldsFormat, Object> {

    private String minField;

    private String maxField;

    @Override
    public void initialize(MinMaxFieldsFormat constraintAnnotation) {
        minField = constraintAnnotation.minField();
        maxField = constraintAnnotation.maxField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Integer minFieldValue = (Integer) getFieldValue(value, minField);
            Integer maxFieldValue = (Integer) getFieldValue(value, maxField);
            return minFieldValue == null || maxFieldValue == null || minFieldValue <= maxFieldValue;
        } catch (Exception e) {
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
}
