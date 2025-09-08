package com.supermarket.mvcsupermarket.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraints.br.CPF;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public void initialize(CPF cpf) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) {
            return false;
        }

        // Validação de CPF
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int d1 = 0, d2 = 0;
        for (int i = 0; i < 9; i++) {
            d1 += (10 - i) * Character.getNumericValue(cpf.charAt(i));
            d2 += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }

        d1 = 11 - (d1 % 11);
        if (d1 >= 10) {
            d1 = 0;
        }
        d2 += 2 * d1;
        d2 = 11 - (d2 % 11);
        if (d2 >= 10) {
            d2 = 0;
        }

        return cpf.charAt(9) == Character.forDigit(d1, 10) && cpf.charAt(10) == Character.forDigit(d2, 10);
    }
}