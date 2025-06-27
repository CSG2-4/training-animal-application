package com.example.training_animal_application.model.vo;


public class AnimalWeight extends Weight {
    public AnimalWeight(int value) {
        super(value);
    }

    // 戻り値をintではなくWeightにすることで、補正後の体重を、体重以外の用途で利用されることを禁止できる。
    public Weight getHousedWeight() {
        int v = getValue();
        if (v < 10) {
            return new Weight(v);
        }
        if (v < 50) {
            return new Weight(v + 1);
        }

        if (v < 100) {
            return new Weight(v + 3);
        }
        return new Weight(v + 10);
    }

}
