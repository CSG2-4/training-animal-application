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
    }
}
