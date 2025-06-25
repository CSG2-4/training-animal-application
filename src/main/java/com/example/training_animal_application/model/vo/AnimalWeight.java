package com.example.training_animal_application.model.vo;

import lombok.Value;

// Weightを継承しているので、Weightクラスにweightの比較メソッドを用意すれば「檻の最大重量と動物の体重で比較」が実現できる
public class AnimalWeight extends Weight {
  public AnimalWeight(int value) {
    super(value);
  }

  // 戻り値をintではなくweightにすることで、補正後の体重を体重以外の用途で利用されることを禁止できる。
  // 補正するメソッドはサブクラスにも持たせているので「動物の体重のみ補正をかけれて」を実現
  public Weight getHouseWeight() {
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
