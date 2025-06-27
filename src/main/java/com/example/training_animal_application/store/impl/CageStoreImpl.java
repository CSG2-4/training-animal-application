package com.example.training_animal_application.store.impl;

import com.example.training_animal_application.entity.CageEntity;
import com.example.training_animal_application.store.CageStore;
import com.example.training_animal_application.store.StoreBase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CageStoreImpl extends StoreBase<CageEntity> implements CageStore, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        CageEntity c1 = new CageEntity("0001", "普通の檻", 500, 5);
        CageEntity c2 = new CageEntity("0002", "頑丈な檻", 9999, 5);
        CageEntity c3 = new CageEntity("0003", "広い檻", 500, 99);
        insert(c1.getCageId(), c1);
        insert(c2.getCageId(), c2);
        insert(c3.getCageId(), c3);
    }

    @Override
    public CageEntity findByKey(String k) {
        return get(k);
    }
}
