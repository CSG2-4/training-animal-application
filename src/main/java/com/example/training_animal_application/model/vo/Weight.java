package com.example.training_animal_application.model.vo;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Weight {
    protected static String UNIT = "Kg";
    int value;

    public String getWeight() {
        return value + UNIT;
    }

    public static Weight of(int value) {
        return new Weight(value);
    }
}
