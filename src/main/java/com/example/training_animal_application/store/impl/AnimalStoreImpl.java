package com.example.training_animal_application.store.impl;

import com.example.training_animal_application.entity.AnimalEntity;
import com.example.training_animal_application.store.AnimalStore;
import com.example.training_animal_application.store.StoreBase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AnimalStoreImpl extends StoreBase<AnimalEntity> implements AnimalStore, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        AnimalEntity a1 = new AnimalEntity("1001", "うさぎ", 5);
        AnimalEntity a2 = new AnimalEntity("1002", "ライオン", 500);
        AnimalEntity a3 = new AnimalEntity("1003", "象", 1000);
        insert(a1.getAnimalId(), a1);
        insert(a2.getAnimalId(), a2);
        insert(a3.getAnimalId(), a3);
    }

    @Override
    public AnimalEntity findByKey(String k) {
        return get(k);
    }
}
