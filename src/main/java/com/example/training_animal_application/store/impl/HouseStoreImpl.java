package com.example.training_animal_application.store.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.store.HouseStore;
import com.example.training_animal_application.store.StoreBase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HouseStoreImpl extends StoreBase<HouseEntity> implements HouseStore, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        HouseEntity h1 = new HouseEntity("0001", "1001");
        HouseEntity h2 = new HouseEntity("0001", "1001");
        HouseEntity h3 = new HouseEntity("0001", "1002");
        insert(UUID.randomUUID().toString(), h1);
        insert(UUID.randomUUID().toString(), h2);
        insert(UUID.randomUUID().toString(), h3);
    }
}
