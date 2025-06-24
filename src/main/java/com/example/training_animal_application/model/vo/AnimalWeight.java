package com.example.training_animal_application.model.vo;

import lombok.Value;

@Value
public class AnimalWeight extends Weight {

    public int getAdjustedWeight() {
        if (value < 10) return value;
        else if (value < 50) return value + 1;
        else if (value < 100) return value + 3;
        else return value + 10;
    }
}
