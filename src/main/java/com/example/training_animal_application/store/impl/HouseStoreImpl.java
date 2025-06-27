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
    insert(h1,getCageId(), h1);
  }
}
