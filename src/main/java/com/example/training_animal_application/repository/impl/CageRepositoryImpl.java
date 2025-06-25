package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.CageRepository;
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

    public Cage find(String cageId) {
        CageEntity entity = cageStore.findByKey(cageId);
        return toModel(entity);
    }

    private Cage toModel(CageEntity entity) {
        if (entity == null) {
            return null;
        }
        Cage model = new Cage();
        model.setCageId(entity.getCageId());
        model.setName(entity.getName());
        Weight weight = new Weight();
        weight.setValue(entity.getLimitWeight());
        model.setLimitWeight(weight);
        model.setLimitSize(new Size(entity.getLimitSize()));
        return model;
    }
}
