package br.com.dld.checkpoint.serialization.enumerated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author David Duarte
 */
public class ValidEnumeratedValidator implements ConstraintValidator<ValidEnumerated, Enum<?>> {

    private ValidEnumerated resolver;

    @Override
    public void initialize(ValidEnumerated resolver) {
        this.resolver = resolver;
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Object[] enumValues = this.resolver.enumClass().getEnumConstants();

        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value.name().equals(enumValue.toString()) || (this.resolver.ignoreCase()
                        && value.name().equalsIgnoreCase(enumValue.toString()))) {
                    return true;
                }
            }
        }

        return false;
    }
}
