package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.repository.HouseRepository;
import com.example.training_animal_application.store.HouseStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {
    private final HouseStore houseStore;

    @Override
    public void insert(House model) {
        HouseEntity e = new HouseEntity(model.getCageId(), model.getAnimalId());
        houseStore.insert(UUID.randomUUID().toString(), e);
    }
}
