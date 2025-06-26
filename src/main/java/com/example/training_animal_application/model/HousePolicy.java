package com.example.training_animal_application.model;

public interface HousePolicy {
    House.executeResult execute(Cage cage, Animal animal);
}
