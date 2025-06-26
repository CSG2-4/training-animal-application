package com.example.training_animal_application.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor
public class Weight {
    private static final String UNIT = "Kg";
    private final int value;

    // getWeight()はfinalでoverrideを禁止しているので、全てのサブクラスで「Kg」が担保され、「システム全体での重さの単位は「Kg」で統一」を実現できる
    public final String getWeight() {
        return value + UNIT;
    }
}
