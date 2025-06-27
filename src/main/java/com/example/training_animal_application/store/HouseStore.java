package com.example.training_animal_application.store;

import com.example.training_animal_application.entity.HouseEntity;

import java.util.List;

public interface HouseStore extends Store<HouseEntity>{
    List<HouseEntity> findByCageId(String cageId);
}
