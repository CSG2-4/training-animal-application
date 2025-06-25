package com.example.training_animal_application.model.vo;

import lombok.Value;

@Value
public class AnimalWeight extends Weight {
    public AnimalWeight(int value) {
        super(value);
    }

    public int getAdjustedWeight() {
        if (getValue() < 10) return getValue();
        else if (getValue() < 50) return getValue() + 1;
        else if (getValue() < 100) return getValue() + 3;
        else return getValue() + 10;
    }
}
