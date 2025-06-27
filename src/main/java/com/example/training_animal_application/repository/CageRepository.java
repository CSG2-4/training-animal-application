package com.example.training_animal_application.repository;

import com.example.training_animal_application.model.Cage;

import java.util.List;

public interface CageRepository {
    List<Cage> fetch();
    Cage find(String cageId);
}
