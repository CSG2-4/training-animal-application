package com.example.training_animal_application.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Weight {
    private static final String UNIT = "Kg";
    private final int value;

    // getWeight()はfinalでoverrideを禁止しているので、全てんpサブクラスでKgで表示する単位のロジックを保証できる。
    public final String getWeight() {
        return value + UNIT;
    }

    // 体重同士の足し算を行うロジック
    // Weightクラス引数も戻り値もWeightクラスなため、体重と体重以外の数値で計算を行うことを禁止できる
    public final Weight increase(Weight w) {
        return new Weight(getValue() + w.getValue());
    }

    // Weightクラス引数も戻り値もWeightクラスなため、体重と体重以外の数値で計算を行うことを禁止できる
    public final Weight reduce(Weight w) {
        return new Weight(getValue() - w.getValue());
    }
}
