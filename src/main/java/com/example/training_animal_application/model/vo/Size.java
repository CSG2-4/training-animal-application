package com.example.training_animal_application.model.vo;

import lombok.Value;

@Value
public class Size {
    private static final String UNIT = "匹";
    int value;

    public String getSize() {
        return value + UNIT;
    }

    public boolean equals(Size s){
        return s.getValue() == value;
    }
}
