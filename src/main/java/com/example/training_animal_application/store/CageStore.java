package com.example.training_animal_application.store;

import com.example.training_animal_application.entity.CageEntity;

public interface CageStore extends Store<CageEntity>{
    CageEntity findByKey(String k);
}
