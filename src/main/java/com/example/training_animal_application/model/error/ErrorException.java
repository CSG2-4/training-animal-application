package com.example.training_animal_application.model.error;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ErrorException extends Exception {
    String message;
}
