package com.example.training_animal_application.model.vo;

import lombok.Data;

@Data
public class Weight {
    private static final String UNIT = "kg";
    int value;

    public String getWeight() {
        return value + UNIT;
    }
}
