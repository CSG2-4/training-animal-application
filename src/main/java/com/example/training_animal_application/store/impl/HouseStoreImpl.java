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
    HouseEntity h1 = new HouseEntity("OK", null);
    HouseEntity h2 = new HouseEntity("WARN", "最大重量が" +  + "オーバーしました。");
    HouseEntity h3 = new HouseEntity("ERROR", "最大収容数を超えています");
    insert(h1,get(), h1);
    insert(h2,get(), h2);
    insert(h3,get(), h3);
  }
}
