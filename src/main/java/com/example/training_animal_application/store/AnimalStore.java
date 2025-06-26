package com.example.training_animal_application.store;

import com.example.training_animal_application.entity.AnimalEntity;

public interface AnimalStore extends Store<AnimalEntity>{
  AnimalEntity findByKey(String k);
}
