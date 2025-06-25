package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.repository.HouseRepository;
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
    public List<House> fetch() {
        List<HouseEntity> entities = houseStore.findAll();
        return entities.stream().map(this::toModel).toList();
    }

    @Override
    public List<House> fetchBy(String cageId) {
        List<HouseEntity> entities = houseStore.findAll();
        return entities.stream().filter(e -> cageId.equals(e.getCageId())).map(this::toModel).toList();
    }

    @Override
    public void insert(House model) {
        HouseEntity e = new HouseEntity(model.getCageId(), model.getAnimalId());
        houseStore.insert(UUID.randomUUID().toString(), e);
    }

    private House toModel(HouseEntity entity) {
        if (entity == null) {
            return null;
        }
        House model = new House();
        model.setCageId(entity.getCageId());
        model.setAnimalId(entity.getAnimalId());
        return model;
    }
}
