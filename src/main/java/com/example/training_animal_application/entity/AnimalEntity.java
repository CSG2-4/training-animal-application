package com.example.training_animal_application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalEntity {
  private String animalId;
  private String name;
  private int weight;
}
