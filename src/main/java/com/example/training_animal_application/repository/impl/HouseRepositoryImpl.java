package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.AddHouse;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import com.example.training_animal_application.store.CageStore;
import com.example.training_animal_application.store.HouseStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {

    private final HouseStore houseStore;

    @Override
    public void insert(AddHouse house) {
        houseStore.insert(UUID.randomUUID().toString(),toEntity(house));
    }

    private HouseEntity toEntity(House model) {
        HouseEntity entity = new HouseEntity();
        entity.setCageId(model.getCageId());
        entity.setAnimalId(model.getAnimalId());
        entity.setHousedAt(model.getHousedAt());
        return entity;
    }
}
