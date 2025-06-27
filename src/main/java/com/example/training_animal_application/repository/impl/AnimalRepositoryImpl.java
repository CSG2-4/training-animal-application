package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.AnimalEntity;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.store.AnimalStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepository {

  private final AnimalStore animalStore;

  @Override
  public List<Animal> fetch() {
    List<AnimalEntity> entities = animalStore.findAll();
    return entities.stream().map(this::toModel).toList();
  }

  @Override
  public Animal findById(String animalId) {
    AnimalEntity entity = animalStore.findByKey(animalId);
    return toModel(entity);
  }

  private Animal toModel(AnimalEntity entity) {
    Animal model = new Animal();
    model.setAnimalId(entity.getAnimalId());
    model.setName(entity.getName());
    model.setWeight(new AnimalWeight(entity.getWeight()));
    return model;
  }
}
