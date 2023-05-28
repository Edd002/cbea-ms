package com.pucminas.cbea.global.util.string;

import com.pucminas.cbea.global.util.Validation;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfStringValidator implements ConstraintValidator<ValueOfString, String> {

    private String value;

    @Override
    public void initialize(ValueOfString constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (NumberUtils.isParsable(value) || Validation.isNotNull(value) && (value.equals(Boolean.TRUE.toString()) || value.equals(Boolean.FALSE.toString()))) {
            return false;
        }
        return true;
    }
}