package com.academia.equipamentos.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public boolean isEmailValid(String email) {
        // Validação simples de e-mail
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && email.matches(emailRegex);
    }

    public boolean isPasswordStrong(String password) {
        // Validação simples para verificar a força da senha
        return password != null && password.length() >= 8 && password.matches(".*\\d.*");
    }
}
