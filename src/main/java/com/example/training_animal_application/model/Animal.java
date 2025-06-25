package com.example.training_animal_application.model;

import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Weight;
import lombok.Data;

@Data
public class Animal {
  private String animalId;
  private String name;
  private AnimalWeight weight;
}
