package com.restapi.practice.util.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Component
public final class RequestValidation {
    public final boolean invalidRequest(BindingResult validation) {
        return validation.hasErrors();
    }

    public final List<String> getErrors(BindingResult validation) {
        List<String> errors = new ArrayList<>();
        validation.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return errors;
    }
}
