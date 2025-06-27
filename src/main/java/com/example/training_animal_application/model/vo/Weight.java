package com.example.training_animal_application.model.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Weight {
    private static final String UNIT = "Kg";
    private final int value;

    public final String getWeight() {
        return value + UNIT;
    }
}
