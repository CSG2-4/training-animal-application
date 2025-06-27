package com.example.training_animal_application.repository;

import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;

import java.util.List;

public interface AnimalRepository {
    List<Animal> fetch();

    Animal fetchByAnimalId(String animaId);
}
