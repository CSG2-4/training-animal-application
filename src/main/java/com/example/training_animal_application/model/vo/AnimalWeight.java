package com.example.training_animal_application.model.vo;

import lombok.Getter;

@Getter
public class AnimalWeight extends Weight {

  public AnimalWeight(int originalWeight) {
    super(calcCorrectedWeight(originalWeight));
  }

  private static int calcCorrectedWeight(int weight) {
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
