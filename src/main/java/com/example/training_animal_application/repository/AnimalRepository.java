package com.example.training_animal_application.repository;

import com.example.training_animal_application.model.Animal;

import java.util.List;

public interface AnimalRepository {
  List<Animal> fetch();
  Animal findById(String animalId);
}
