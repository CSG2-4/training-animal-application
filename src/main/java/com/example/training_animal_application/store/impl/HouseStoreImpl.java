package com.example.training_animal_application.store.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.store.HouseStore;
import com.example.training_animal_application.store.StoreBase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class HouseStoreImpl extends StoreBase<HouseEntity> implements HouseStore, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        HouseEntity h1 = new HouseEntity("0001", "1001");
        HouseEntity h2 = new HouseEntity("0001", "1001");
        HouseEntity h3 = new HouseEntity("0001", "1002");
        insert(h1.getCageId(), h1);
        insert(h2.getCageId(), h2);
        insert(h3.getCageId(), h3);
    }
}
