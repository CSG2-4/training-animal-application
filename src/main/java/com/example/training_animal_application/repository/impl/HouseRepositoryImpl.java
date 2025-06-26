package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.repository.HouseRepository;
import com.example.training_animal_application.store.HouseStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {

  private final HouseStore houseStore;

  @Override
  public List<House> fetch() {
    List<HouseEntity> entities = houseStore.findAll();
    return entities.stream().map(this::toModel).toList();
  }

  private House toModel(HouseEntity entity) {
    House model = new House();
    model.setCageId(entity.getCageId());
    model.setAnimalId(entity.getAnimalId());
    return model;
  }
}
