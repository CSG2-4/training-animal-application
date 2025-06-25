package com.example.training_animal_application.model.vo;

import lombok.Value;

@Value
public class Weight {
    private static final String UNIT = "Kg";
    int value;

    public String getWeight() {
        return value + UNIT;
    }
}
