package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        assertTrue(violations.isEmpty());
    }

    // Email inválido
    @Test
    public void testInvalidEmailFormat() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "no-es-un-email",
                Role.USER, "aaaaaaA1");
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        assertFalse(violations.isEmpty());
    }

    // Contraseña sin mayúsculas
    @Test
    public void testInvalidPasswordTooWeak() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "usuario@email.com",
                Role.USER, "abcdefg1"); // sin mayúsculas
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        assertFalse(violations.isEmpty());
    }
}