package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.AnimalEntity;
import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.store.AnimalStore;
import com.example.training_animal_application.store.CageStore;
import com.example.training_animal_application.store.HouseStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CageRepositoryImpl implements CageRepository {

    private final CageStore cageStore;
    private final HouseStore houseStore;
    private final AnimalStore animalStore;

    @Override
    public List<Cage> fetch() {
        List<CageEntity> entities = cageStore.findAll();
        return entities.stream()
                .map(entity -> toCageModel(entity, getCurrentAnimalEntities(entity.getCageId())))
                .toList();
    }

    public Cage find(String cageId) {
        CageEntity cageEntity = cageStore.findByKey(cageId);
        List<AnimalEntity> currentAnimalEntities = getCurrentAnimalEntities(cageId);
        return toCageModel(cageEntity, currentAnimalEntities);
    }

    private List<AnimalEntity> getCurrentAnimalEntities(String cageId) {
        List<HouseEntity> houseEntities = houseStore.findAll();
        List<AnimalEntity> animalEntities = animalStore.findAll();

        return houseEntities.stream()
                .filter(h -> cageId.equals(h.getCageId()))
                .map(houseEntity -> animalEntities.stream()
                        .filter(a -> a.getAnimalId().equals(houseEntity.getAnimalId()))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

    private Cage toCageModel(CageEntity cageEntity, List<AnimalEntity> animalEntities) {
        if (cageEntity == null) {
            return null;
        }
        Cage model = new Cage();
        model.setCageId(cageEntity.getCageId());
        model.setName(cageEntity.getName());
        model.setLimitWeight(new Weight(cageEntity.getLimitWeight()));
        model.setLimitSize(new Size(cageEntity.getLimitSize()));
        if (!animalEntities.isEmpty()) {
            model.setAnimals(animalEntities.stream().map(this::toAnimalModel).toList());
        } else {
            model.setAnimals(Collections.emptyList());
        }
        return model;
    }

    private Animal toAnimalModel(AnimalEntity entity) {
        if (entity == null) {
            return null;
        }
        Animal model = new Animal();
        model.setAnimalId(entity.getAnimalId());
        model.setName(entity.getName());
        model.setWeight(new AnimalWeight(entity.getWeight()));
        return model;
    }
}
