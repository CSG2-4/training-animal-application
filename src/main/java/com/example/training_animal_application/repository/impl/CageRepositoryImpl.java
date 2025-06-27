package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.store.CageStore;
import com.example.training_animal_application.store.HouseStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CageRepositoryImpl implements CageRepository {

    private final CageStore cageStore;
    private final HouseStore houseStore;
    private final AnimalRepository animalRepository;

    @Override
    public List<Cage> fetch() {
        List<CageEntity> entities = cageStore.findAll();
        List<Cage> cages = entities.stream().map(this::toModel).toList();

        for (Cage cage : cages) {
            cage.setAnimals(findAnimals(cage.getCageId()));
        }

        return cages;
    }

    @Override
    public Cage findByCageId(String cageId) {
        CageEntity cageEntity = cageStore.findByKey(cageId);

        if (cageEntity == null) {
            return null;
        }

        Cage cage = toModel(cageEntity);
        cage.setAnimals(findAnimals(cageId));
        return cage;
    }

    private List<Animal> findAnimals(String cageId) {
        List<HouseEntity> houseEntities = houseStore.findByCageId(cageId);

        if (CollectionUtils.isEmpty(houseEntities)) {
            return new ArrayList();
        }

        return houseEntities.stream()
                .map(HouseEntity::getAnimalId)
                .map(animalRepository::fetchByAnimalId)
                .filter(Objects::nonNull).collect(Collectors.toList());

    }

    private Cage toModel(CageEntity entity) {
        Cage model = new Cage();
        model.setCageId(entity.getCageId());
        model.setName(entity.getName());
        model.setLimitWeight(new Weight(entity.getLimitWeight()));
        model.setLimitSize(new Size(entity.getLimitSize()));
        return model;
    }
}
