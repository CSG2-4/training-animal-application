package com.example.training_animal_application.store.impl;

import com.example.training_animal_application.entity.HouseEntity;
import com.example.training_animal_application.store.HouseStore;
import com.example.training_animal_application.store.StoreBase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class HouseStoreImpl extends StoreBase<HouseEntity> implements HouseStore, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
      // noop
    }

    @Override
    public List<HouseEntity> findByCageId(String cageId) {
        return findAll().stream().filter((e)-> cageId.equals(e.getCageId())).sorted(Comparator.comparing(HouseEntity::getHousedAt)).toList();
    }
}
