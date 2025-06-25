package com.example.training_animal_application.model.vo;

import lombok.Value;

@Value
public class AnimalWeight {

  private static final String UNIT = "Kg";
  int value;

  public String getWeight() {
    return value + UNIT;
  }

  public int calcCorrectedWeight(int weight) {
    if (weight >= 100) {
      return weight + 10;
    } else if (weight >= 50) {
      return weight + 3;
    } else if (weight >= 10) {
      return weight + 1;
    } else {
      return weight;
    }
  }
}
