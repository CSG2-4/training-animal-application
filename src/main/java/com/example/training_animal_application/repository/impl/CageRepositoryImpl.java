package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.AnimalEntity;
import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.store.AnimalStore;
import com.example.training_animal_application.store.CageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CageRepositoryImpl implements CageRepository {

    private final CageStore cageStore;

    @Override
    public List<Cage> fetch() {
        List<CageEntity> entities = cageStore.findAll();
        return entities.stream().map(this::toModel).toList();
    }

    @Override
    public Cage findById(String cageId) {
        CageEntity entity = cageStore.findByKey(cageId);
        return toModel(entity);
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
