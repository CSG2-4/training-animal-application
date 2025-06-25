package com.example.training_animal_application.repository;

import com.example.training_animal_application.model.House;

import java.util.List;

public interface HouseRepository {
    List<House> fetch();
    List<House> fetchBy(String cageId);
    void insert(House model);
}
